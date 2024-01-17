package io.americanexpress.synapse.utilities.common.email;

import org.apache.commons.lang3.StringUtils;
import java.util.regex.Pattern;

/**
 * {@code EmailAddressValidator} is used for email address validation.
 *
 * @author Wendy Hu
 */
public class EmailAddressValidator {

    /**
     * The email address regex.
     */
    public static final String EMAIL_ADDRESS_REGEX_RULE = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";

    /**
     * The email address pattern.
     */
    private static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(EMAIL_ADDRESS_REGEX_RULE, Pattern.CASE_INSENSITIVE);

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private EmailAddressValidator() {}

    /**
     * Checks if an email address is valid.
     *
     * @param emailAddress the email address
     * @return true if email address is valid
     */
    public static boolean isValid(String emailAddress) {
        return StringUtils.isNotBlank(emailAddress) && EMAIL_ADDRESS_PATTERN.matcher(emailAddress).matches();
    }

}
