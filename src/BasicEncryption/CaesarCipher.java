package BasicEncryption;

public class CaesarCipher {
    public static void main(String[] args) {
        System.out.println(encrypt("Ủy viên Ban Chấp hành Trung ương Đảng khóa XI, Ủy viên Ban Thường vụ Đảng ủy Công an Trung ương, Thứ trưởng Bộ Công an; tháng 9/2014 thăng cấp bậc hàm Thượng tướng", 3));
        System.out.println(decrypt("G DL K RF Q  RQJ O P\n", 3));

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
                } else{
                builder.append(textToChar);
            }
        }
        return builder.toString();
    }
    public static String decrypt(String txt, int k){
        return encrypt(txt, 26-(k % 26));
    }


}
