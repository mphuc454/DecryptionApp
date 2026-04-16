package AsymmetricEncryption;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class RSA {
        KeyPair keyPair;
        PrivateKey privateKey;
        PublicKey publicKey;

        public String encryptBase64(String data) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
            return Base64.getEncoder().encodeToString(encrypt(data));
            
        }
        public void genKey() throws NoSuchAlgorithmException {
                KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
                generator.initialize(2048);
                keyPair = generator.generateKeyPair();
                publicKey = keyPair.getPublic();
                privateKey = keyPair.getPrivate();
        }

    private byte[] encrypt(String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        byte[] in = data.getBytes(StandardCharsets.UTF_8);
        cipher.init(cipher.ENCRYPT_MODE, publicKey);
        byte[] out = cipher.doFinal(in);
        return out;
    }
    private String decrypt(String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        byte[] in = Base64.getDecoder().decode(data);
        cipher.init(cipher.DECRYPT_MODE, privateKey);
        byte[] out = cipher.doFinal(in);
        return new String(out, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
            RSA rsa = new RSA();
            rsa.genKey();
            String e = rsa.encryptBase64("Arsenal chỉ có 4 lần vào đến Bán kết cúp C1 trong lịch sử. 2 lần diễn ra dưới thời Wenger, 2 lần còn lại là dưới thời Arteta");
            System.out.println(e);
            String d = rsa.decrypt(e);
            System.out.println(d);

    }
}
