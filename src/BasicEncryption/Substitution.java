package BasicEncryption;

public class Substitution {
    public static void main(String[] args) {
        String txt = "Ủy viên Ban Chấp hành Trung ương Đảng khóa XI, Ủy viên Ban Thường vụ Đảng ủy Công an Trung ương, Thứ trưởng Bộ Công an; tháng 9/2014 thăng cấp bậc hàm Thượng tướng";
        String e = encrypt(txt);
        String d = decrypt(e);
        System.out.println(e);
        System.out.println(d);

    }
    static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String REALPHABET = "QWERTYUIOPASDFGHJKLZXCVBNMabcdefghijklmnopqrstuvwxyz";

    public static String encrypt(String txt){
        StringBuilder builder = new StringBuilder();
        for(char c: txt.toCharArray()){
            if(ALPHABET.indexOf(c) != -1){
                builder.append(REALPHABET.charAt(ALPHABET.indexOf(c)));
            }else{
                builder.append(c);
            }
        }
        return builder.toString();
    }
    public static String decrypt(String txt){
        StringBuilder builder = new StringBuilder();
        for(char c: txt.toCharArray()){
            if(REALPHABET.indexOf(c) != -1){
                builder.append(ALPHABET.charAt(REALPHABET.indexOf(c)));
            }else{
                builder.append(c);
            }
        }
        return builder.toString();
    }
}
