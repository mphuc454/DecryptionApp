package SymmetricEncoding;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AES {
    private SecretKey key;
    IvParameterSpec iv;
    public SecretKey genKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        key = keyGenerator.generateKey();
        return key;
    }
    public IvParameterSpec genIV(){
        iv = new IvParameterSpec(new byte[16]);
        return iv;
    }
    public void loadKey(SecretKey key){
        this.key = key;
    }
    public byte[] encrypt(String txt) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, this.key, iv);
        byte[] data = txt.getBytes(StandardCharsets.UTF_8);
        return cipher.doFinal(data);
    }
    public String decrypt(byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher =  Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, this.key, iv);
        byte[] bytes = cipher.doFinal(data);
        return new String(bytes, StandardCharsets.UTF_8);
    }
    public boolean encryptFile(String src, String des) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, this.key, iv);
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(des));
        CipherInputStream in = new CipherInputStream(input, cipher);
        int i;
        byte[] read = new byte[1024];
        byte[] re = null;
        while ((i = input.read(read)) != -1) {
            output.write(read, 0, i);
        }
        read = cipher.doFinal();
        if(read != null){
            output.write(read);
        }
        in.close();
        output.flush();
        output.close();
        return true;
    }
    public boolean decryptFile(String src, String des) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, this.key, iv);
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(des));
        CipherOutputStream out = new CipherOutputStream(output, cipher);
        int i;
        byte[] read = new byte[1024];
        byte[] re = null;
        while ((i = input.read(read)) != -1) {
            output.write(read, 0, i);
        }
        read = cipher.doFinal();
        if(read != null){
            out.write(read);
        }
        input.close();
        output.flush();
        output.close();
        return true;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        String text = " . Gáy sao cũng chấp nhận vì đó là sự thật hiển nhiên ";
        AES aes = new AES();
        aes.genKey();
        aes.genIV();
        byte[] re = aes.encrypt(text);
        System.out.println(Base64.getEncoder().encodeToString(re));
        System.out.println(aes.decrypt(re));

    }

}
