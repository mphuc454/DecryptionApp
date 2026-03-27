package BasicEncryption;

public class HillCipher {
    public static void main(String[] args) {
        int[][] key = {{3,3},{2,5}};
        System.out.println(encrypt("bcd", key));
    }
    public static int[] nhanMatrix(int[][] key, int[]vector){
        int[] res = new int[2];
        res[0] = (key[0][0] * vector[0] + key[0][1] * vector[1]) % 26;
        res[1] = (key[1][0] * vector[0] + key[1][1] * vector[1]) % 26;
        return res;
    }
    public static String encrypt(String text, int[][] k){
        StringBuilder builder = new StringBuilder();
        text = text.toUpperCase();
        if(text.length() % 2 != 0){
            text += 'X';
        }
        for (int i = 0; i < text.length(); i+=2){
            int[] vector = {text.charAt(i) - 'A', text.charAt(i+1) - 'A'};
            int [] res = nhanMatrix(k, vector);
            builder.append((char) (res[0] + 'A'));
            builder.append((char) (res[1] + 'A'));
        }

        return builder.toString();
    }
}
