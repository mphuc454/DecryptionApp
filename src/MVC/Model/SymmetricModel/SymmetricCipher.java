package MVC.Model.SymmetricModel;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SymmetricCipher {
    private SecretKey key;
    IvParameterSpec iv;
    public SecretKey genKey(String algorithm, int keySize) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(keySize);
        key = keyGenerator.generateKey();
        return key;
    }
    public IvParameterSpec genIV(String algorithm){
        int block =  (algorithm.equalsIgnoreCase("DES"))? 8 : 16;
        iv = new IvParameterSpec(new byte[block]);
        return iv;
    }
    public String transformation(String algorithm, String mode, String padding){
        return algorithm +"/"+ mode + "/"+ padding;
    }
    public void loadKey(SecretKey key){
        this.key = key;
    }

    public byte[] encrypt(String txt, String algorithm, String mode, String padding) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(transformation(algorithm, mode, padding));
        if(mode.equalsIgnoreCase("ECB")){
            cipher.init(Cipher.ENCRYPT_MODE, this.key);
        }else{
            cipher.init(Cipher.ENCRYPT_MODE, this.key, iv);
        }
        byte[] data = txt.getBytes(StandardCharsets.UTF_8);
        return cipher.doFinal(data);
    }

    public String decrypt(byte[] data, String algorithm, String mode, String padding) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher =  Cipher.getInstance(transformation(algorithm, mode, padding));
        if(mode.equalsIgnoreCase("ECB")){
            cipher.init(Cipher.DECRYPT_MODE, this.key);
        }else{
            cipher.init(Cipher.DECRYPT_MODE, this.key, iv);
        }
        byte[] bytes = cipher.doFinal(data);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public boolean encryptFile(String src, String des, String algorithm, String mode, String padding) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(transformation(algorithm, mode, padding));
        if(mode.equalsIgnoreCase("ECB")){
            cipher.init(Cipher.ENCRYPT_MODE, this.key);
        }else{
            cipher.init(Cipher.ENCRYPT_MODE, this.key, iv);
        }
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(des));
        CipherOutputStream cipherOutputStream = new CipherOutputStream(output, cipher);
        int i;
        byte[] read = new byte[1024];
        while ((i = input.read(read)) != -1) {
            cipherOutputStream.write(read, 0, i);
        }
        cipherOutputStream.close();
        input.close();
        return true;
    }
    public boolean decryptFile(String src, String des, String algorithm, String mode, String padding) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(transformation(algorithm, mode, padding));
        if(mode.equalsIgnoreCase("ECB")){
            cipher.init(Cipher.DECRYPT_MODE, this.key);
        }else{
            cipher.init(Cipher.DECRYPT_MODE, this.key, iv);
        }
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(des));
        CipherInputStream cipherInputStream = new CipherInputStream(input, cipher);
        int i;
        byte[] read = new byte[1024];
        while ((i = cipherInputStream.read(read)) != -1) {
            output.write(read, 0, i);
        }
        cipherInputStream.close();
        output.close();
        return true;
    }
}
