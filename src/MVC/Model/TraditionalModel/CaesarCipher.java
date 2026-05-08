package MVC.Model.TraditionalModel;

public class CaesarCipher{
    String Alphabet = "aăâbcdđeêghiklmnoôơpqrstuưvxyAĂÂBCDĐEÊGHIKLMNOÔƠPQRSTUƯVXY";

    public String encrypt(String txt, int k) {
        StringBuilder builder = new StringBuilder();
        for(char c: txt.toCharArray()){
            if( Alphabet.indexOf(c) != -1){
                int index = (Alphabet.indexOf(c) + k) % Alphabet.length();
                builder.append(Alphabet.charAt(index));
            }else{
                builder.append(c);
            }
        }
        return builder.toString();
    }

    public String decrypt(String txt, int k) {
        return encrypt(txt, Alphabet.length() - (k % Alphabet.length()));
    }

}
