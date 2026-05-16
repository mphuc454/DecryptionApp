package MVC.Model.OtherModel;

import javax.crypto.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
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
    public void setPrivateKey(PrivateKey privateKey){
        this.privateKey = privateKey;
    }
    public void setPublicKey(PublicKey publicKey){
        this.publicKey = publicKey;
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

    public byte[] encrypt(String data, String algorithm, String mode, String padding) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(transformation(algorithm, mode, padding));
        byte[] in = data.getBytes(StandardCharsets.UTF_8);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] out = cipher.doFinal(in);
        return out;
    }

    public String decrypt(String data, String algorithm, String mode, String padding) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(transformation(algorithm, mode, padding));
        byte[] in = Base64.getDecoder().decode(data);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] out = cipher.doFinal(in);
        return new String(out, StandardCharsets.UTF_8);
    }
}
