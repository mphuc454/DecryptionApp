package TraditionalEncryption;

public class AffineCipher {
    public static void main(String[] args) {
        String txt = "Ủy viên Ban Chấp hành Trung ương Đảng khóa XI, Ủy viên Ban Thường vụ Đảng ủy Công an Trung ương, Thứ trưởng Bộ Công an; tháng 9/2014 thăng cấp bậc hàm Thượng tướng";

        String maHoa = encrypt(txt, 3, 7);
       String giaiMa = decrypt(maHoa, 3, 7);
        System.out.println(maHoa);
        System.out.println(giaiMa);
    }
    static final String ALPHABET = "aăâbcdđeêghiklmnoôơpqrstuưvxyAĂÂBCDĐEÊGHIKLMNOÔƠPQRSTUƯVXY";

    public static String encrypt(String txt, int a, int b){
        StringBuilder builder = new StringBuilder();
        for(char c: txt.toCharArray()){
            if( ALPHABET.indexOf(c) != -1){
                int index = (a * ALPHABET.indexOf(c) + b) % ALPHABET.length();
                builder.append(ALPHABET.charAt(index));
            }else{
                builder.append(c);
            }
        }
        return builder.toString();
    }
    public static int modNghichDao(int a, int m){
        a =  a % m;
        for(int i = 1; i < m; i++){
            if((a * i) % m == 1){
                return i;
            }
        }
        return -1;
    }
    public static String decrypt(String txt, int a, int b){
        int a_inv = modNghichDao(a, ALPHABET.length());
        StringBuilder result = new StringBuilder();
        for(char c : txt.toCharArray()){
            if(ALPHABET.indexOf(c) != -1){
                int newIndex = (a_inv * (ALPHABET.indexOf(c) - b + ALPHABET.length())) % ALPHABET.length();
                result.append(ALPHABET.charAt(newIndex));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

}
