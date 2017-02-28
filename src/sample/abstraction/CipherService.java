package sample.abstraction;

/**
 * @author dbudko
 */
public interface CipherService {
    String encryptText(String plainText);
    String decryptText(String encryptedText);
}
