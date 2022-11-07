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
package io.americanexpress.function.greeting.function

import io.americanexpress.function.greeting.model.Greeting
import org.springframework.http.HttpStatus
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import org.springframework.web.server.ResponseStatusException
import java.util.function.Function

/**
 * `Greet` contains the Greet function.
 */
open class Greet(private val validator: Validator) : Function<Greeting, Greeting?> {

    /**
     * Applies this function to the given argument.
     *
     * @param greeting the function argument
     * @return the function result
     */
    override fun apply(greeting: Greeting): Greeting? {
        var output: Greeting? = null
            val errors: Errors = BeanPropertyBindingResult(greeting, Greeting::class.java.name)
        validator.validate(greeting, errors)
            if (errors.allErrors.isEmpty()) {
                output = Greeting()
                output.message = "Hello " + greeting.message
            } else {
                onValidationErrors(errors)
            }
            return output
    }

    /**
     * Returns new ResponseStatusException when there are validation errors.
     *
     * @param errors the errors
     * @return the response status exception
     */
    protected fun onValidationErrors(errors: Errors): ResponseStatusException {
        throw ResponseStatusException(HttpStatus.BAD_REQUEST, errors.allErrors[0].defaultMessage)
    }
}
