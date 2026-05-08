package MVC.Model.TraditionalModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SubstitutionCipher{
    String Alphabet = "aăâbcdđeêghiklmnoôơpqrstuưvxyAĂÂBCDĐEÊGHIKLMNOÔƠPQRSTUƯVXY";;

    public String randomMappingAlphabet(){
        List li = new ArrayList();
        for(char c: Alphabet.toCharArray()){
            li.add(c);
        }
        Collections.shuffle(li);
        StringBuilder builder = new StringBuilder();
        for(Object c : li){
            builder.append(c);
        }
        return builder.toString();
    }
    String mappingAlphabet = randomMappingAlphabet();

    public String encrypt(String txt) {
        StringBuilder builder = new StringBuilder();
        for(char c: txt.toCharArray()){
            if(Alphabet.indexOf(c) != -1){
                builder.append(mappingAlphabet.charAt(Alphabet.indexOf(c)));
            }else{
                builder.append(c);
            }
        }
        return builder.toString();
    }
    public String decrypt(String txt) {
        StringBuilder builder = new StringBuilder();
        for(char c: txt.toCharArray()){
            if(mappingAlphabet.indexOf(c) != -1){
                builder.append(Alphabet.charAt(mappingAlphabet.indexOf(c)));
            }else{
                builder.append(c);
            }
        }
        return builder.toString();
    }

}
