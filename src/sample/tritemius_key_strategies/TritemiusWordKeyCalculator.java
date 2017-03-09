package sample.tritemius_key_strategies;

import sample.abstraction.CipherService;

/**
 * @author DBudko
 */
public class TritemiusWordKeyCalculator implements TritemiusKeyCalculator {
    private String keyWord;
    private String alphabet;

    public TritemiusWordKeyCalculator(String keyWord) {
        this.keyWord = keyWord;
        alphabet = CipherService.alphabet;
    }

    @Override
    public Integer getOffset(Integer inputIndex) {
        Integer keyWordIndex = inputIndex % keyWord.length();
        Character keyChar = keyWord.charAt(keyWordIndex);
        return alphabet.indexOf(keyChar);
    }
}
