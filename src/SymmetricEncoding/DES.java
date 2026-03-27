package SymmetricEncoding;

import javax.crypto.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class DES {
    private SecretKey key;
    public SecretKey createKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56);
        key = keyGenerator.generateKey();
        return key;
    }
    public void loadKey(SecretKey key){
        this.key = key;
    }
    public byte[] encypt(String txt) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, this.key);
        byte[] data = txt.getBytes(StandardCharsets.UTF_8);
        return cipher.doFinal(data);
    }
    public String decrypt(byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher =  Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, this.key);
        byte[] bytes = cipher.doFinal(data);
        return new String(bytes, StandardCharsets.UTF_8);
    }
    public boolean encryptFile(String src, String des) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, this.key);
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(des));
        CipherInputStream in = new CipherInputStream(input, cipher);
        int i;
        byte[] read = new byte[1024];
        byte[] re = null;
        while ((i = in.read(read)) != -1) {
            output.write(read, 0, i);
        }
        in.close();
        output.flush();
        output.close();
        return true;
    }
}
