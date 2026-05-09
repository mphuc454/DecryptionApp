package MVC.Model.AsymmetricModel;

import javax.crypto.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class RSA {
    private KeyPair keyPair;
    private PrivateKey privateKey;
    private PublicKey publicKey;



    public String encryptBase64(String data) throws NoSuchPaddingException, IllegalBlockSizeException,
            NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return Base64.getEncoder().encodeToString(encrypt(data));
    }

    public void genKey() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
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
    public void setPublicKey(PublicKey publicKey){
        this.publicKey = publicKey;
    }
    public void setPrivateKey(PrivateKey privateKey){
        this.privateKey = privateKey;
    }

    public void encryptFile(String src, String des) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        File f = new File(src);
        if(f.exists()){
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
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

    public void decryptFile(String src, String des) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
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

    private byte[] encrypt(String data) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        byte[] in = data.getBytes(StandardCharsets.UTF_8);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] out = cipher.doFinal(in);
        return out;
    }

    private String decrypt(String data) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        byte[] in = Base64.getDecoder().decode(data);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] out = cipher.doFinal(in);
        return new String(out, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException {
        RSA rsa = new RSA();
        rsa.genKey();

        System.out.println("Public Key: " + rsa.getPublicKey());
        System.out.println("Private Key: " + rsa.getPrivateKey());

        String f = "C:\\Users\\mphuc\\Downloads\\New Microsoft Word Document.docx";
        String enc = "C:\\Users\\mphuc\\Downloads\\2.doc";
        String dec = "C:\\Users\\mphuc\\Downloads\\3.doc";

        rsa.encryptFile(f, enc);

        rsa.decryptFile(enc, dec);
    }

}
