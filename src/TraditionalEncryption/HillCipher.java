package TraditionalEncryption;

public class HillCipher {
    public static void main(String[] args) {
        String txt = "Ủy viên Ban Chấp hành Trung ương Đảng khóa XI, Ủy viên Ban Thường vụ Đảng ủy Công an Trung ương, Thứ trưởng Bộ Công an; tháng 9/2014 thăng cấp bậc hàm Thượng tướng";
        int[][] key = {{3, 2}, {5, 7}};
        String e = encrypt(txt, key);
        String d = decrypt(e, key, txt);
        System.out.println(e);
        System.out.println(d);
    }

    static final String ALPHABET = "aăâbcdđeêghiklmnoôơpqrstuưvxyAĂÂBCDĐEÊGHIKLMNOÔƠPQRSTUƯVXY";

    public static int[] nhanMatrix(int[][] key, int[] vector, int mod) {
        int[] res = new int[2];
        res[0] = (key[0][0] * vector[0] + key[0][1] * vector[1]) % mod;
        res[1] = (key[1][0] * vector[0] + key[1][1] * vector[1]) % mod;
        return res;
    }

    public static String encrypt(String text, int[][] k) {
        String plaintext = text.replaceAll("[^aăâbcdđeêghiklmnoôơpqrstuưvxyAĂÂBCDĐEÊGHIKLMNOÔƠPQRSTUƯVXY]", "");

        if (plaintext.length() % 2 != 0) {
            plaintext += 'X';
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i += 2) {
            int[] vector = {ALPHABET.indexOf(plaintext.charAt(i)), ALPHABET.indexOf(plaintext.charAt(i + 1))};
            int[] res = nhanMatrix(k, vector, ALPHABET.length());
            builder.append(ALPHABET.charAt(res[0]));
            builder.append(ALPHABET.charAt(res[1]));
        }

        return builder.toString();
    }

    public static int modInverse(int a, int mod) {
        a %= mod;
        for (int i = 1; i < mod; i++) {
            if ((a * i) % mod == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void inverseMatrix(int[][] key, int[][] invkey, int mod) {
        int a = key[0][0];
        int b = key[0][1];
        int c = key[1][0];
        int d = key[1][1];

        int det = a * d - b * c;
        det = (det % mod + mod) % mod;
        int invDet = modInverse(det, mod);

        invkey[0][0] = (d * invDet % mod + mod) % mod;
        invkey[0][1] = (-b * invDet % mod + mod) % mod;
        invkey[1][0] = (-c * invDet % mod + mod) % mod;
        invkey[1][1] = (a * invDet % mod + mod) % mod;
    }

    public static String decrypt(String text, int[][] k, String originalText) {
        int[][] invkey = new int[2][2];
        inverseMatrix(k, invkey, ALPHABET.length());

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            int[] vector = {ALPHABET.indexOf(text.charAt(i)), ALPHABET.indexOf(text.charAt(i + 1))};
            int[] res = nhanMatrix(invkey, vector, ALPHABET.length());
            builder.append(ALPHABET.charAt(res[0]));
            builder.append(ALPHABET.charAt(res[1]));
        }

        StringBuilder result = new StringBuilder();
        int decryptedIndex = 0;
        for (char c : originalText.toCharArray()) {
            if (ALPHABET.contains(String.valueOf(c))) {
                    result.append(builder.charAt(decryptedIndex));
                    decryptedIndex++;
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}