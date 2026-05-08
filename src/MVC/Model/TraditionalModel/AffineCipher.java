package MVC.Model.TraditionalModel;

public class AffineCipher {

    String Alphabet = "aăâbcdđeêghiklmnoôơpqrstuưvxyAĂÂBCDĐEÊGHIKLMNOÔƠPQRSTUƯVXY";

    public String encrypt(String txt, int a, int b){
        StringBuilder builder = new StringBuilder();
        for(char c: txt.toCharArray()){
            if( Alphabet.indexOf(c) != -1){
                int index = (a * Alphabet.indexOf(c) + b) % Alphabet.length();
                builder.append(Alphabet.charAt(index));
            }else{
                builder.append(c);
            }
        }
        return builder.toString();
    }
    public int modNghichDao(int a, int m){
        a =  a % m;
        for(int i = 1; i < m; i++){
            if((a * i) % m == 1){
                return i;
            }
        }
        return -1;
    }
    public String decrypt(String txt, int a, int b){
        int a_inv = modNghichDao(a, Alphabet.length());
        StringBuilder result = new StringBuilder();
        for(char c : txt.toCharArray()){
            if(Alphabet.indexOf(c) != -1){
                int newIndex = (a_inv * (Alphabet.indexOf(c) - b + Alphabet.length())) % Alphabet.length();
                result.append(Alphabet.charAt(newIndex));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

}
