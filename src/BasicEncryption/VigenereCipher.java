package BasicEncryption;

public class VigenereCipher {
    public static void main(String[] args) {
       String key = keyGenerator("á à ấ Z%$yldi Vaệt Fum", "SUA");
        String kq = decrypt("á à ấ Z%$yldi Vaệt Fum", key);
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
public static String encrypt(String txt, String k) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < txt.length(); i++) {
            char myText = txt.charAt(i);
            if (isAsciiUpper(myText)) {
                char keyChar = Character.toUpperCase(k.charAt(i));
                builder.append((char) ((myText - 'A' + keyChar - 'A') % 26 + 'A'));
            } else if (isAsciiLower(myText)) {
                char keyChar = Character.toLowerCase(k.charAt(i));
                builder.append((char) ((myText - 'a' + keyChar - 'a') % 26 + 'a'));
            } else {
                builder.append(myText);
            }
        }
        return builder.toString();
    }

public static String decrypt(String txt, String k) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < txt.length(); i++) {
        char myText = txt.charAt(i);
        if (isAsciiUpper(myText)) {
            char keyChar = Character.toUpperCase(k.charAt(i));
            builder.append((char) ((myText - 'A' - (keyChar - 'A') + 26) % 26 + 'A'));
        } else if (isAsciiLower(myText)) {
            char keyChar = Character.toLowerCase(k.charAt(i));
            builder.append((char) ((myText - 'a' - (keyChar - 'a') + 26) % 26 + 'a'));
        } else {
            builder.append(myText);
        }
    }
    return builder.toString();
}
    private static boolean isAsciiUpper(char c) {
        return  c >= 'A' && c <= 'Z';
    }
    private static boolean isAsciiLower(char c) {
        return c >= 'a' && c <= 'z';
    }
}
