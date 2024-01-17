package io.americanexpress.synapse.utilities.common.email;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.validation.ConstraintValidatorContext;

@ExtendWith(MockitoExtension.class)
public class EmailAddressConstraintTest {

    private final EmailAddress emailAddress = Mockito.mock(EmailAddress.class);

    private final ConstraintValidatorContext constraintValidatorContext = Mockito.mock(ConstraintValidatorContext.class);

    private final EmailAddressConstraint emailAddressConstraint = new EmailAddressConstraint();

    @BeforeEach
    void init() {
        emailAddressConstraint.initialize(emailAddress);
    }

    @ParameterizedTest
    @ValueSource(strings = {"email@synapse.com", "EMAIL@SYNAPSE.COM", "email1234@SYNAPSE.COM"})
    void isValid_givenValidEmailAddress_expectedTrue(String email) {
        var testModel = new TestModel();
        testModel.setEmail(email);

        boolean actual = emailAddressConstraint.isValid(testModel.getEmail(), constraintValidatorContext);
        Assertions.assertTrue(actual);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"    ", "EMAIL@SYNAPSE", "email1234@.COM"})
    void isValid_givenInvalidEmailAddress_expectedFalse(String email) {
        var testModel = new TestModel();
        testModel.setEmail(email);

        boolean actual = emailAddressConstraint.isValid(testModel.getEmail(), constraintValidatorContext);
        Assertions.assertFalse(actual);
    }
}
