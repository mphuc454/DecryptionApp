package MVC.Model.TraditionalModel;

public class SubstitutionCipher{
    String Alphabet = "aăâbcdđeêghiklmnoôơpqrstuưvxyAĂÂBCDĐEÊGHIKLMNOÔƠPQRSTUƯVXY";
    String mappingALPHABET = "YXVƯUTSRQPƠÔONMLKIHGÊEĐDCBÂĂAyxvưutsrqpơôonmlkihgêeđdcbâăa";

    public String encrypt(String txt) {
        StringBuilder builder = new StringBuilder();
        for(char c: txt.toCharArray()){
            if(Alphabet.indexOf(c) != -1){
                builder.append(mappingALPHABET.charAt(Alphabet.indexOf(c)));
            }else{
                builder.append(c);
            }
        }
        return builder.toString();
    }
    public String decrypt(String txt) {
        StringBuilder builder = new StringBuilder();
        for(char c: txt.toCharArray()){
            if(mappingALPHABET.indexOf(c) != -1){
                builder.append(Alphabet.charAt(mappingALPHABET.indexOf(c)));
            }else{
                builder.append(c);
            }
        }
        return builder.toString();
    }

}
