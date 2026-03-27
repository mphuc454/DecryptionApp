package BasicEncryption;

public class AffineCipher {
    public static void main(String[] args) {
       String maHoa = encrypt("KHOA CONG NGHE THONG TIN", 3, 7);
       String giaiMa = decrypt("VAXD RXQT QTAF GAXQT GHQ", 3, 7);
        System.out.println(maHoa);
        System.out.println(giaiMa);
    }
    public static String encrypt(String txt, int a, int b){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i <txt.length(); i++){
            char texToChar = txt.charAt(i);
            if(Character.isUpperCase(texToChar)){
                builder.append((char) ('A' + (a * (texToChar - 'A') + b) % 26));
            } else if (Character.isLowerCase(texToChar)) {
                builder.append((char) ('a' + (a * (texToChar - 'a') + b) % 26));
            }else{
                builder.append(texToChar);
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
        int num_mod = modNghichDao(a, 26);
        return encrypt(txt, num_mod, (26 - num_mod * b % 26) % 26);
    }
}
