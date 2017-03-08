package sample.implementation;

import sample.abstraction.CipherService;


/**
 * @author DBudko
 */
public class CaesarCipherService extends CipherService {
    private Long key;


    public CaesarCipherService(Long key) throws Exception {
        this.key = key;
    }


    protected String crypt (String text,Integer keyModifier) {
        StringBuilder outputText = new StringBuilder();
        for (Character letter : text.toCharArray()) {
            Long decryptedIndex = Math.floorMod((alphabet.indexOf(letter) + (key * keyModifier) + alphabetLength),alphabetLength);
            outputText.append(alphabet.charAt(decryptedIndex.intValue()));
        }
        return outputText.toString();
    }
}
