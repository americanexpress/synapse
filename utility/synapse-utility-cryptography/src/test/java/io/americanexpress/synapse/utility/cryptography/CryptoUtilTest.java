package io.americanexpress.synapse.utility.cryptography;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code CryptoUtilTest} tests the {@link CryptoUtil}
 */
class CryptoUtilTest {

    private static final String ENCRYPTED_VALUE = "feR+vAk+Ysys9pOeEWupVQ==";

    private static final String DECRYPTED_VALUE = "184981684091";

    @Test
    void tryDecrypt_givenEncryptedString_expectedDecryptedString() {
        String decrypted = CryptoUtil.tryDecrypt(ENCRYPTED_VALUE);
        assertEquals(DECRYPTED_VALUE, decrypted);
    }

    @Test
    void decrypt_givenEncryptedString_expectedDecryptedString() {
        String decrypted = CryptoUtil.decrypt(ENCRYPTED_VALUE);
        assertEquals(DECRYPTED_VALUE, decrypted);
    }

    @Test
    void tryEncrypt_givenDecryptedString_expectedEncryptedString() {
        String decrypted = CryptoUtil.tryEncrypt(DECRYPTED_VALUE);
        assertEquals(ENCRYPTED_VALUE, decrypted);
    }

    @Test
    void encrypt_givenDecryptedString_expectedEncryptedString() {
        String encrypt = CryptoUtil.encrypt(DECRYPTED_VALUE);
        assertEquals(ENCRYPTED_VALUE, encrypt);
    }
}
