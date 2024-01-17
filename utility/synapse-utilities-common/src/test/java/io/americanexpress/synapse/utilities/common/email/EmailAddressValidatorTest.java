package io.americanexpress.synapse.utilities.common.email;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class EmailAddressValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"test@synapse.com", "TEST@DOMAIN.COM", "123test789TEST@TEST.COM"})
    void isValid_givenValidEmailAddress_expectedTrue(String email) {
        Assertions.assertTrue(EmailAddressValidator.isValid(email));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "test.com", "test@com", "test@.com", "test@com."})
    void isValid_givenInvalidEmailAddress_expectedFalse(String email) {
        Assertions.assertFalse(EmailAddressValidator.isValid(email));
    }

}
