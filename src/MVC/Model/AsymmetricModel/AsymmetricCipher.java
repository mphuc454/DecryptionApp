package MVC.Model.AsymmetricModel;

import javax.crypto.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class AsymmetricCipher {
    private KeyPair keyPair;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public String encryptBase64(String data, String algorithm, String mode, String padding) throws NoSuchPaddingException, IllegalBlockSizeException,
            NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return Base64.getEncoder().encodeToString(encrypt(data, algorithm, mode, padding));
    }

    public void genKey(String algorithm, int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(algorithm);
        generator.initialize(keySize);
        keyPair = generator.generateKeyPair();
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
    }

    public KeyPair getKeyPair(){
        return keyPair;
    }
    public PublicKey getPublicKey(){
        return publicKey;
    }
    public PrivateKey getPrivateKey(){
        return privateKey;
    }

    public String transformation(String algorithm, String mode, String padding){
        return algorithm +"/"+ mode + "/"+ padding;
    }

    public void encryptFile(String src, String des, String algorithm, String mode, String padding) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        File f = new File(src);
        if(f.exists()){
            Cipher cipher = Cipher.getInstance(transformation(algorithm, mode, padding));
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            try (FileInputStream fis = new FileInputStream(src);
                 DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(des)))) {

                byte[] input = new byte[245];
                int byteRead;

                while ((byteRead = fis.read(input)) != -1) {
                    byte[] encrypted = cipher.doFinal(input, 0, byteRead);
                    dos.writeInt(encrypted.length);
                    dos.write(encrypted);
                    dos.flush();
                }
            }
        } else {
            System.out.println("File chưa được tạo");
        }
    }

    public void decryptFile(String src, String des, String algorithm, String mode, String padding) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(transformation(algorithm, mode, padding));
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(src)));
             FileOutputStream fos = new FileOutputStream(des)) {
            while (dis.available() > 0) {
                int length = dis.readInt();
                byte[] encrypted = new byte[length];
                dis.readFully(encrypted);
                byte[] decrypted = cipher.doFinal(encrypted);
                fos.write(decrypted);
            }
        }
    }

    private byte[] encrypt(String data, String algorithm, String mode, String padding) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(transformation(algorithm, mode, padding));
        byte[] in = data.getBytes(StandardCharsets.UTF_8);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] out = cipher.doFinal(in);
        return out;
    }

    private String decrypt(String data, String des, String algorithm, String mode, String padding) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(transformation(algorithm, mode, padding));
        byte[] in = Base64.getDecoder().decode(data);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] out = cipher.doFinal(in);
        return new String(out, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException {
        AsymmetricCipher rsa = new AsymmetricCipher();
        rsa.genKey("RSA", 2048);

        System.out.println("Public Key: " + rsa.getPublicKey());
        System.out.println("Private Key: " + rsa.getPrivateKey());

        String f = "C:\\Users\\mphuc\\Downloads\\22130218_NguyenHoangPhuc_1.docx";
        String enc = "C:\\Users\\mphuc\\Downloads\\2.doc";
        String dec = "C:\\Users\\mphuc\\Downloads\\3.doc";

        rsa.encryptFile(f, enc, "RSA", "ECB", "PKCS1Padding");

        rsa.decryptFile(enc, dec, "RSA", "ECB", "PKCS1Padding");
    }

}
