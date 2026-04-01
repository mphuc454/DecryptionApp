package BasicEncryption;

import java.text.Normalizer;

public class HillCipher {
    public static void main(String[] args) {
        String txt = "Ủy viên Ban Chấp hành Trung ương Đảng khóa XI, Ủy viên Ban Thường vụ Đảng ủy Công an Trung ương, Thứ trưởng Bộ Công an; tháng 9/2014 thăng cấp bậc hàm Thượng tướng";
        int[][] key = {{3,3},{2,5}};
        String e = encrypt(txt, key);
        String d = decrypt(e, key);
        System.out.println(e);
        System.out.println(d);
    }
    static final int MOD = 65536;
    public static int[] nhanMatrix(int[][] key, int[]vector){
        int[] res = new int[2];
        res[0] = ((key[0][0] * vector[0] + key[0][1] * vector[1]) % MOD);
        res[1] = ((key[1][0] * vector[0] + key[1][1] * vector[1]) % MOD);
        return res;
    }
    public static String encrypt(String text, int[][] k){
        StringBuilder builder = new StringBuilder();
        if(text.length() % 2 != 0){
            text += 'X';
        }
        for (int i = 0; i < text.length(); i+=2){
            int[] vector = {text.charAt(i), text.charAt(i+1)};
            int [] res = nhanMatrix(k, vector);
            builder.append((char) (res[0]));
            builder.append((char) (res[1]));
        }

        return builder.toString();
    }
    public static String decrypt(String text, int[][] k){
        StringBuilder builder = new StringBuilder();
        int[][] invK = matrixNghicDao(k);

        for (int i = 0; i < text.length(); i+=2){
            int[] vector = {text.charAt(i), text.charAt(i+1)};
            int[] res = nhanMatrix(invK, vector);
            builder.append((char)(res[0]));
            builder.append((char)(res[1]));
        }

        return builder.toString();
    }
    private static int[][] matrixNghicDao(int[][] k) {
        int det = k[0][0]*k[1][1] - k[0][1]*k[1][0];
        det = mod(det, MOD);
        int invDet = modInverse(det, MOD);
        int[][] inv = new int[2][2];
        inv[0][0] =  k[1][1];
        inv[0][1] = -k[0][1];
        inv[1][0] = -k[1][0];
        inv[1][1] =  k[0][0];

        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                inv[i][j] = mod(inv[i][j] * invDet, MOD);
            }
        }
        return inv;
    }
    public static int mod(int a, int m) {
        return (a % m + m) % m;
    }

    public static int modInverse(int a, int m) {
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 0;
    }

}
