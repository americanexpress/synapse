/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.americanexpress.synapse.utilities.common.encoding;

import io.americanexpress.synapse.utilities.common.cryptography.CryptoUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CryptoUtilTest {

    private static final String ENCRYPTED_VALUE = "feR+vAk+Ysys9pOeEWupVQ==";

    private static final String DECRYPTED_VALUE = "184981684091";

    @Test
    void tryJasyptDecrypt_givenEncryptedString_expectedDecryptedString() {
        String decrypted = CryptoUtil.tryDecrypt(ENCRYPTED_VALUE);
        assertEquals(DECRYPTED_VALUE, decrypted);
    }

    /**
     * Testing to make sure decryption will not return a string that has a number in it.
     */
    @Test
    void jasyptDecrypt_givenEncryptedString_expectedDecryptedString() {
        String decrypted = CryptoUtil.decrypt(ENCRYPTED_VALUE);
        assertEquals(DECRYPTED_VALUE, decrypted);
    }

    @Test
    void tryJasyptEncrypt_givenDecryptedString_expectedEncryptedString() {
        String decrypted = CryptoUtil.tryEncrypt(DECRYPTED_VALUE);
        assertEquals(ENCRYPTED_VALUE, decrypted);
    }

    @Test
    void jasyptEncrypt_givenDecryptedString_expectedEncryptedString() {
        String encrypt = CryptoUtil.encrypt(DECRYPTED_VALUE);
        assertEquals(ENCRYPTED_VALUE, encrypt);
    }
}
