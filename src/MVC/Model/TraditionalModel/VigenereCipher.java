package MVC.Model.TraditionalModel;

public class VigenereCipher {

    String Alphabet = "aăâbcdđeêghiklmnoôơpqrstuưvxyAĂÂBCDĐEÊGHIKLMNOÔƠPQRSTUƯVXY";;

    public String keyGenerator(String txt, String K){
        StringBuilder builderKey = new StringBuilder();
        int j = 0;
        for (int i = 0; i < txt.length() ; i++) {
            char c = txt.charAt(i);
           if(Alphabet.indexOf(c) != -1){
               builderKey.append(K.charAt(j % K.length()));
               j++;
           }else{
               builderKey.append(c);
           }
        }
        return builderKey.toString();
    }
public String encrypt(String txt, String k) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < txt.length() ; i++) {
            int txtIndex = Alphabet.indexOf(txt.charAt(i));
            int keyIndex = Alphabet.indexOf(k.charAt(i));
            if(txtIndex != -1 && keyIndex != -1){
                int index = (txtIndex + keyIndex) % Alphabet.length();
                builder.append(Alphabet.charAt(index));
            }else{
                builder.append(txt.charAt(i));
            }
    }
        return builder.toString();
    }

public String decrypt(String txt, String k) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < txt.length() ; i++) {
        int txtIndex = Alphabet.indexOf(txt.charAt(i));
        int keyIndex = Alphabet.indexOf(k.charAt(i));
        if(txtIndex != -1 && keyIndex != -1){
            int index = (txtIndex - keyIndex + Alphabet.length()) % Alphabet.length();
            builder.append(Alphabet.charAt(index));
        }else{
            builder.append(txt.charAt(i));
        }
    }
    return builder.toString();
}
}
