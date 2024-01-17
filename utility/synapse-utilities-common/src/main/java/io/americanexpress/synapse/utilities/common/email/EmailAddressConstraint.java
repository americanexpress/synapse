package io.americanexpress.synapse.utilities.common.email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * {@code EmailAddressConstraint} is used for constraining {@link EmailAddress} annotation.
 */
public class EmailAddressConstraint implements ConstraintValidator<EmailAddress, String> {

    /**
     * Checks if an email address is valid.
     *
     * @param emailAddress the email address
     * @return true if email address is valid
     */
    @Override
    public boolean isValid(String emailAddress, ConstraintValidatorContext constraintValidatorContext) {
        return EmailAddressValidator.isValid(emailAddress);
    }

}
