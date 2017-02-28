package sample.implementation;

import sample.abstraction.CipherService;

import java.util.Locale;

/**
 * @author dbudko
 */
public class CaesarCipherService implements CipherService {
    private Long key;

    private String alphabet = "abcdefghijklmnoprstvuwxyzABCDEFGHIJKLMNOPRSTVUWXYZабвгдеёжзийклмнопрстуфхцчшщъыьэюяіАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯІ123456789,./`!@#$%^&*()_-+=- ";
    private int alphabetLength = alphabet.length();

    public CaesarCipherService(Long key) throws Exception {
        this.key = key;
    }

    @Override
    public String encryptText(String plainText) {
        StringBuilder outputText = new StringBuilder();
        for (Character letter : plainText.toCharArray()) {
            Long encryptedIndex = (alphabet.indexOf(letter) + key) % alphabetLength;
            outputText.append(alphabet.charAt(encryptedIndex.intValue()));
        }
        return outputText.toString();
    }

    @Override
    public String decryptText(String encryptedText) {
        StringBuilder outputText = new StringBuilder();
        for (Character letter : encryptedText.toCharArray()) {
            Long decryptedIndex = (alphabet.indexOf(letter) - key + alphabetLength) % alphabetLength;
            outputText.append(alphabet.charAt(decryptedIndex.intValue()));
        }
        return outputText.toString();
    }
}
