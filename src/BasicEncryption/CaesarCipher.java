package BasicEncryption;

public class CaesarCipher {
    public static void main(String[] args) {
        System.out.println(encrypt("DAI HOC NONG LAM", 3));
        System.out.println(decrypt("GDL KRF QRQJ ODP", 3));

    }
    public static String encrypt(String txt, int k){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < txt.length(); i++) {
            char textToChar = txt.charAt(i);
            if(Character.isUpperCase(textToChar)){
                char encryptUpper= (char) ((textToChar - 'A' + k) % 26 + 'A');
                builder.append(encryptUpper);
            } else if (Character.isLowerCase(textToChar)) {
                char encryptLower = (char) ((textToChar - 'a' + k) % 26 + 'a');
                builder.append(encryptLower);
            }else {
                builder.append(textToChar);
            }
        }
        return builder.toString();
    }
    public static String decrypt(String txt, int k){
        return encrypt(txt, 26-(k % 26));
    }

}
