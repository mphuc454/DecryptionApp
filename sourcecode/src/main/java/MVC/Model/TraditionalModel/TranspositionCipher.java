package MVC.Model.TraditionalModel;

public class TranspositionCipher {

    public String encrypt(String txt, int k){
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
    public String decrypt(String txt, int k){
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
