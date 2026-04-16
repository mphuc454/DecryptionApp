package TraditionalEncryption;

public class CaesarCipher {
    public static void main(String[] args) {
        String txt = "Ủy viên Ban Chấp hành Trung ương Đảng khóa XI, Ủy viên Ban Thường vụ Đảng ủy Công an Trung ương, Thứ trưởng Bộ Công an; tháng 9/2014 thăng cấp bậc hàm Thượng tướng";
        String e = encrypt(txt, 3);
        String d =  decrypt(e, 3);
        System.out.println(e);
        System.out.println(d);

    }
    static final String ALPHABET = "aăâbcdđeêghiklmnoôơpqrstuưvxyAĂÂBCDĐEÊGHIKLMNOÔƠPQRSTUƯVXY";

    public static String encrypt(String txt, int k){
        StringBuilder builder = new StringBuilder();
       for(char c: txt.toCharArray()){
           if( ALPHABET.indexOf(c) != -1){
               int index = (ALPHABET.indexOf(c) + k) % ALPHABET.length();
               builder.append(ALPHABET.charAt(index));
           }else{
               builder.append(c);
           }
       }
        return builder.toString();
    }
    public static String decrypt(String txt, int k){
        return encrypt(txt, ALPHABET.length() - (k % ALPHABET.length()));
    }


}
