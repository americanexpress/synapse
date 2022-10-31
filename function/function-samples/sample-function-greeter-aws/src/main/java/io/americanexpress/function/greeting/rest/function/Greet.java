package io.americanexpress.function.greeting.rest.function;

import io.americanexpress.function.greeting.rest.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Function;

public class Greet implements Function<Greeting, Greeting> {

    @Autowired
    protected Validator validator;

    @Override
    public Greeting apply(Greeting greeting) {
        Greeting output = null;
        Errors errors = new BeanPropertyBindingResult(greeting, Greeting.class.getName());
        this.validator.validate(greeting, errors);
        if (errors.getAllErrors().isEmpty()) {
            output = new Greeting();
            output.setMessage("Hello " + greeting.getMessage());
        } else {
            onValidationErrors(errors);
        }
        return output;
    }

    protected ResponseStatusException onValidationErrors(Errors errors) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getAllErrors().get(0).getDefaultMessage());
    }


}