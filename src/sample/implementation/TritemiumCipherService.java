package sample.implementation;

import sample.abstraction.CipherService;
import sample.tritemius_key_strategies.TritemiusKeyCalculator;

/**
 * @author DBudko
 */
public class TritemiumCipherService extends CipherService {
    private TritemiusKeyCalculator tritemiusKeyCalculator;

    public TritemiumCipherService(TritemiusKeyCalculator tritemiusKeyCalculator) {
        this.tritemiusKeyCalculator = tritemiusKeyCalculator;
    }


    protected String crypt (String encryptedText,Integer keyModifier) {
        StringBuilder outputText = new StringBuilder();
        int i = 0;
        for (Character letter : encryptedText.toCharArray()) {
            Integer key = tritemiusKeyCalculator.getOffset(i);
            Integer decryptedIndex = Math.floorMod((alphabet.indexOf(letter) + (key * keyModifier) + alphabetLength),alphabetLength);
            outputText.append(alphabet.charAt(decryptedIndex));
            i++;
        }
        return outputText.toString();
    }
}
