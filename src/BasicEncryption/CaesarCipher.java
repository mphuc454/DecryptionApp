package BasicEncryption;

public class CaesarCipher {
    public static void main(String[] args) {
        System.out.println(encrypt("Đ%A*I H$ỌC N33ÔNG L@M", 3));
//        System.out.println(decrypt("G DL K RF Q  RQJ O P\n", 3));

    }
    public static String encrypt(String txt, int k){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < txt.length(); i++) {
            char textToChar = txt.charAt(i);
                if(isAsciiUpper(textToChar)){
                    char encryptUpper= (char) ((textToChar - 'A' + k) % 26 + 'A');
                    builder.append(encryptUpper);
                } else if (isAsciiLower(textToChar)) {
                    char encryptLower = (char) ((textToChar - 'a' + k) % 26 + 'a');
                    builder.append(encryptLower);
                } else{
                builder.append(textToChar);
            }
        }
        return builder.toString();
    }
    public static String decrypt(String txt, int k){
        return encrypt(txt, 26-(k % 26));
    }
    private static boolean isAsciiUpper(char c) {
        return  c >= 'A' && c <= 'Z';
    }
    private static boolean isAsciiLower(char c) {
        return c >= 'a' && c <= 'z';
    }

}
