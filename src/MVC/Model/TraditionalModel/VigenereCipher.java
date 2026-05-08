package MVC.Model.TraditionalModel;

public class VigenereCipher {

    String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String keyGenerator(String txt, String K){
        StringBuilder builderKey = new StringBuilder();
        int j = 0;
        for (int i = 0; i < txt.length() ; i++) {
            char c = txt.charAt(i);
           if(ALPHABET.indexOf(c) != -1){
               builderKey.append(K.charAt(j % K.length()));
               j++;
           }else{
               builderKey.append(c);
           }
        }
        return builderKey.toString();
    }
public String encrypt(String txt, String k) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < txt.length() ; i++) {
            int txtIndex = ALPHABET.indexOf(txt.charAt(i));
            int keyIndex = ALPHABET.indexOf(k.charAt(i));
            if(txtIndex != -1 && keyIndex != -1){
                int index = (txtIndex + keyIndex) % ALPHABET.length();
                builder.append(ALPHABET.charAt(index));
            }else{
                builder.append(txt.charAt(i));
            }
    }
        return builder.toString();
    }

public String decrypt(String txt, String k) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < txt.length() ; i++) {
        int txtIndex = ALPHABET.indexOf(txt.charAt(i));
        int keyIndex = ALPHABET.indexOf(k.charAt(i));
        if(txtIndex != -1 && keyIndex != -1){
            int index = (txtIndex - keyIndex + ALPHABET.length()) % ALPHABET.length();
            builder.append(ALPHABET.charAt(index));
        }else{
            builder.append(txt.charAt(i));
        }
    }
    return builder.toString();
}
    public static void main(String[] args) {

        VigenereCipher vc = new VigenereCipher();

        String plaintext = "HelloWorld";
        String key = "KEY";

        // tạo key đầy đủ
        String fullKey = vc.keyGenerator(plaintext, key);
        System.out.println("Key full : " + fullKey);

        // mã hóa
        String cipher = vc.encrypt(plaintext, fullKey);

        // giải mã
        String decrypt = vc.decrypt(cipher, fullKey);
    }
}
