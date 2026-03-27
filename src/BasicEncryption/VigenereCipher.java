package BasicEncryption;

public class VigenereCipher {
    public static void main(String[] args) {
       String key = keyGenerator("AKKKK", "SU");
        System.out.println(key);
        String kq = decrypt("AKKKK", key);
        System.out.println(kq);
    }
    public static String keyGenerator(String txt, String K){
        StringBuilder builderKey = new StringBuilder();
        int j = 0;
        for (int i = 0; i < txt.length() ; i++) {
            char c = txt.charAt(i);
           if(Character.isLetter(c)){
               builderKey.append(K.charAt(j % K.length()));
               j++;
           }else{
               builderKey.append(c);
           }
        }
        return builderKey.toString();
    }
    public static String encrypt(String txt, String k){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < txt.length(); i++) {
            char myText = txt.charAt(i);
            if(Character.isUpperCase(myText)){
                builder.append((char) ((myText - 'A' + k.charAt(i) - 'A') % 26 + 'A'));
            }else if(Character.isLowerCase(myText)){
                builder.append((char) ((myText - 'a' + k.charAt(i) - 'a') % 26 + 'a'));
            }else{
                builder.append(myText);
            }
        }
        return builder.toString();
    }
    public static String decrypt(String txt, String k){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < txt.length(); i++) {
            char myText = txt.charAt(i);
            if(Character.isUpperCase(myText)){
                builder.append((char) ((myText - 'A' - (k.charAt(i) - 'A') + 26) % 26 + 'A'));
            }else if(Character.isLowerCase(myText)){
                builder.append((char) ((myText - 'a' - (k.charAt(i) - 'a') + 26) % 26 + 'a'));
            }else{
                builder.append(myText);
            }
        }
        return builder.toString();

    }
}
