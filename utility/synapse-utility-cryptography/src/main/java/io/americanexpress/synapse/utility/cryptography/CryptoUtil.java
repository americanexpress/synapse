package io.americanexpress.synapse.utility.cryptography;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.salt.SaltGenerator;
import org.jasypt.salt.ZeroSaltGenerator;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

/**
 * {@code CryptoUtil} is utility class for encryption and decryption methods.
 */
public class CryptoUtil {

    private static final String ENCRYPTION_KEY = System.getenv("ENCRYPTION_KEY");
    private static final String ALGORITHM_KEY = System.getenv("ALGORITHM_KEY");
    private static final XLogger logger = XLoggerFactory.getXLogger(CryptoUtil.class);

    private static final StandardPBEStringEncryptor textEncryptor;

    /**
     * Initializes StandardPBEStringEncryptor.
     */
    static {
        logger.entry();

        textEncryptor = new StandardPBEStringEncryptor();
        if (StringUtils.isNotBlank(ENCRYPTION_KEY) && StringUtils.isNotBlank(ALGORITHM_KEY)) {
            textEncryptor.setAlgorithm(ALGORITHM_KEY);
            textEncryptor.setPassword(ENCRYPTION_KEY);
        } else {
            textEncryptor.setAlgorithm("PBEWITHMD5ANDDES");
            textEncryptor.setPassword("thisIsARandomStringForTestingPleaseSetPasswordInSystemEnv");
        }
        SaltGenerator saltGenerator = new ZeroSaltGenerator();
        textEncryptor.setSaltGenerator(saltGenerator);
        textEncryptor.initialize();

        logger.exit(textEncryptor);
    }

    private CryptoUtil() {
    }

    /**
     * Encrypts the string provided.
     *
     * @param text to be encrypted
     * @return encrypted string
     */
    public static String encrypt(String text) {
        logger.entry(text);

        String encrypted = null;
        if (textEncryptor != null && text != null) {
            encrypted = textEncryptor.encrypt(text).replace("/", "_").replace("\\+", "-");
        }
        return encrypted;
    }

    /**
     * Used to decrypt the encrypted string passed as param. If the text sent to it is a number, it will return that number instead of decrypting.
     *
     * @param text the text to be decrypted.
     * @return the decrypted String
     */
    public static String decrypt(String text) {
        logger.entry(text);
        String decrypted = text;
        if (!StringUtils.isNumeric(text) && (textEncryptor != null && text != null)) {
            String encrypted = text.replace("_", "/").replace("-", "\\+");
            decrypted = textEncryptor.decrypt(encrypted);
        }
        return decrypted;
    }

    public static String tryEncrypt(String text) {
        String encrypted;
        try {
            encrypted = encrypt(text);
        } catch (EncryptionOperationNotPossibleException exception) {
            logger.catching(exception);
            encrypted = text;
        }
        return encrypted;
    }

    public static String tryDecrypt(String text) {
        String decrypted;
        try {
            decrypted = decrypt(text);
        } catch (EncryptionOperationNotPossibleException exception) {
            logger.warn("There was an error trying to decrypt " + text, exception);
            decrypted = text;
        }
        return decrypted;
    }

}

