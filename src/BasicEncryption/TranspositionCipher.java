package BasicEncryption;

public class TranspositionCipher {
    public static void main(String[] args) {
        String txt = "Ủy viên Ban Chấp hành Trung ương Đảng khóa XI, Ủy viên Ban Thường vụ Đảng ủy Công an Trung ương, Thứ trưởng Bộ Công an; tháng 9/2014 thăng cấp bậc hàm Thượng tướng";
        int key = 3;
        String maHoa = encrypt(txt, key);
        String giaiMa = decrypt(maHoa, key);
        System.out.println(maHoa);
        System.out.println(giaiMa);
    }
    public static String encrypt(String txt, int k){
        StringBuilder builder = new StringBuilder();
        int cols = k;
        int rows = (int) Math.ceil((double) txt.length() / cols);
        char[][] matrix = new char[rows][cols];
        int index = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(index < txt.length()){
                    matrix[i][j] = txt.charAt(index++);
                }else{
                    matrix[i][j] = ' ';
                }
            }
        }
        for(int j = 0; j < cols; j++){
            for(int i = 0; i < rows; i++){
                builder.append(matrix[i][j]);
            }
        }
        return builder.toString();
    }
    public static String decrypt(String txt, int k){
        StringBuilder builder = new StringBuilder();
        int cols = k;
        int rows = (int) Math.ceil((double) txt.length() / cols);
        char[][] matrix = new char[rows][cols];
        int index = 0;
        for(int j = 0; j < cols; j++){
            for(int i = 0; i < rows; i++){
                if(index < txt.length()){
                    matrix[i][j] = txt.charAt(index++);
                }else{
                    matrix[i][j] = ' ';
                }
            }
        }
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                builder.append(matrix[i][j]);
            }
        }
        return builder.toString();
    }
}
