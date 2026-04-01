package BasicEncryption;

public class VigenereCipher {
    public static void main(String[] args) {
        String txt = "Ủy viên Ban Chấp hành Trung ương Đảng khóa XI, Ủy viên Ban Thường vụ Đảng ủy Công an Trung ương, Thứ trưởng Bộ Công an; tháng 9/2014 thăng cấp bậc hàm Thượng tướng";
        String key = keyGenerator(txt, "SUA");
        String maHoa = encrypt(txt, key);
        String kq = decrypt(maHoa, key);
        System.out.println(maHoa);
        System.out.println(kq);

    }
    static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String keyGenerator(String txt, String K){
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
public static String encrypt(String txt, String k) {
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

public static String decrypt(String txt, String k) {
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
}
