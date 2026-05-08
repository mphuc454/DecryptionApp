package MVC.Model.TraditionalModel;

public class HillCipher {
    String Alphabet = "aăâbcdđeêghiklmnoôơpqrstuưvxyAĂÂBCDĐEÊGHIKLMNOÔƠPQRSTUƯVXY";
    public int[] nhanMatrix(int[][] key, int[] vector, int mod) {
        int[] res = new int[2];
        res[0] = (key[0][0] * vector[0] + key[0][1] * vector[1]) % mod;
        res[1] = (key[1][0] * vector[0] + key[1][1] * vector[1]) % mod;
        return res;
    }

    public String encrypt(String text, int[][] k) {
        String plaintext = text.replaceAll("[^aăâbcdđeêghiklmnoôơpqrstuưvxyAĂÂBCDĐEÊGHIKLMNOÔƠPQRSTUƯVXY]", "");

        if (plaintext.length() % 2 != 0) {
            plaintext += 'X';
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i += 2) {
            int[] vector = {Alphabet.indexOf(plaintext.charAt(i)), Alphabet.indexOf(plaintext.charAt(i + 1))};
            int[] res = nhanMatrix(k, vector, Alphabet.length());
            builder.append(Alphabet.charAt(res[0]));
            builder.append(Alphabet.charAt(res[1]));
        }
        return builder.toString();
    }

    public int modInverse(int a, int mod) {
        a %= mod;
        for (int i = 1; i < mod; i++) {
            if ((a * i) % mod == 1) {
                return i;
            }
        }
        return -1;
    }

    public void inverseMatrix(int[][] key, int[][] invkey, int mod) {
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

    public String decrypt(String text, int[][] k, String originalText) {
        int[][] invkey = new int[2][2];
        inverseMatrix(k, invkey, Alphabet.length());

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            int[] vector = {Alphabet.indexOf(text.charAt(i)), Alphabet.indexOf(text.charAt(i + 1))};
            int[] res = nhanMatrix(invkey, vector, Alphabet.length());
            builder.append(Alphabet.charAt(res[0]));
            builder.append(Alphabet.charAt(res[1]));
        }

        StringBuilder result = new StringBuilder();
        int index = 0;
        for (char c : originalText.toCharArray()) {
            if (Alphabet.contains(String.valueOf(c))) {
                result.append(builder.charAt(index));
                index++;
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
