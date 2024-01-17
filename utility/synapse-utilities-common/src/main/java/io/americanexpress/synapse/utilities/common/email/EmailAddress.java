package io.americanexpress.synapse.utilities.common.email;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code EmailAddress} annotation used to validate email address field in models.
 */
@Documented
@Constraint(validatedBy = EmailAddressConstraint.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailAddress {

    String message() default "Invalid email address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
