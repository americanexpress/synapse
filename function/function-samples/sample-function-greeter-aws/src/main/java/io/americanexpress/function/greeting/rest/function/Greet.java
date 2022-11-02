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
package io.americanexpress.function.greeting.rest.function;

import io.americanexpress.function.greeting.rest.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
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
