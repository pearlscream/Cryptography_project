package sample.tritemius_key_strategies;

import sample.abstraction.CipherService;

/**
 * Created by Dima on 09.03.2017.
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
