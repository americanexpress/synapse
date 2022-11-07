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
package io.americanexpress.synapse.utilities.common.cryptography;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.salt.SaltGenerator;
import org.jasypt.salt.ZeroSaltGenerator;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class CryptoUtil {

    private static final String ENCRYPTION_KEY = System.getenv("ENCRYPTION_KEY");
    private static final String ALGORITHM_KEY = System.getenv("ALGORITHM_KEY");
    private static final XLogger logger = XLoggerFactory.getXLogger(CryptoUtil.class);

    private static final StandardPBEStringEncryptor textEncryptor;

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

    public static String jasyptEncrypt(String text) {
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
    public static String jasyptDecrypt(String text) {
        logger.entry(text);
        String decrypted = text;
        if (!StringUtils.isNumeric(text) && (textEncryptor != null && text != null)) {
            String encrypted = text.replace("_", "/").replace("-", "\\+");
            decrypted = textEncryptor.decrypt(encrypted);
        }
        return decrypted;
    }

    public static String tryJasyptEncrypt(String text) {
        String encrypted;
        try {
            encrypted = jasyptEncrypt(text);
        } catch (EncryptionOperationNotPossibleException exception) {
            logger.catching(exception);
            encrypted = text;
        }
        return encrypted;
    }

    public static String tryJasyptDecrypt(String text) {
        String decrypted;
        try {
            decrypted = jasyptDecrypt(text);
        } catch (EncryptionOperationNotPossibleException exception) {
            logger.warn("There was an error trying to decrypt " + text, exception);
            decrypted = text;
        }
        return decrypted;
    }

}
