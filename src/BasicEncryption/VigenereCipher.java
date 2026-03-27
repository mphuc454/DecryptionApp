package BasicEncryption;

public class VigenereCipher {
    public static void main(String[] args) {
       String key = keyGenerator("Ủy viên Ban Chấp hành Trung ương Đảng khóa XI, Ủy viên Ban Thường vụ Đảng ủy Công an Trung ương, Thứ trưởng Bộ Công an; tháng 9/2014 thăng cấp bậc hàm Thượng tướng", "SUA");
        String kq = decrypt("Ủy viên Ban Chấp hành Trung ương Đảng khóa XI, Ủy viên Ban Thường vụ Đảng ủy Công an Trung ương, Thứ trưởng Bộ Công an; tháng 9/2014 thăng cấp bậc hàm Thượng tướng", key);
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
            if (Character.isUpperCase(myText)) {
                char keyChar = Character.toUpperCase(k.charAt(i));
                builder.append((char) ((myText - 'A' + keyChar - 'A') % 26 + 'A'));
            } else if (Character.isLowerCase(myText)) {
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
        if (Character.isUpperCase(myText)) {
            char keyChar = Character.toUpperCase(k.charAt(i));
            builder.append((char) ((myText - 'A' - (keyChar - 'A') + 26) % 26 + 'A'));
        } else if (Character.isLowerCase(myText)) {
            char keyChar = Character.toLowerCase(k.charAt(i));
            builder.append((char) ((myText - 'a' - (keyChar - 'a') + 26) % 26 + 'a'));
        } else {
            builder.append(myText);
        }
    }
    return builder.toString();
}
}
