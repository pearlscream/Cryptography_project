package sample.abstraction;

/**
 * @author DBudko
 */
public abstract class CipherService {
    public static String alphabet = "abcdefghijklmnoprstvuwxyzABCDEFGHIJKLMNOPRSTVUWXYZабвгдеёжзийклмнопрстхуфцчшщъыьэюяіАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯІЇїєЄ123456789,./`!@#$%^&*()_-+=- ";
    protected int alphabetLength = alphabet.length();

    public String encryptText(String plainText) {
        return crypt(plainText,1);
    }

    public String decryptText(String encryptedText) {
        return crypt(encryptedText,-1);
    }

    protected abstract String crypt(String text,Integer keyModifier);
}
