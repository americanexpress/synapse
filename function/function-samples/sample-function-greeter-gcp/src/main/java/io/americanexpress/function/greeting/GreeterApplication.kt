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
package io.americanexpress.function.greeting

import org.springframework.boot.autoconfigure.SpringBootApplication
import java.lang.StringBuilder
import kotlin.jvm.JvmStatic
import org.springframework.boot.SpringApplication
import org.springframework.context.annotation.Bean
import java.util.*
import java.util.function.Function

/**
 * `GreeterApplication` starts the Spring Boot Application
 */
@SpringBootApplication
open class GreeterApplication {

    /**
     * Uppercase function.
     *
     * @return the function
     */
    @Bean
    open fun uppercase(): Function<String, String> {
        return Function { value: String -> value.uppercase(Locale.getDefault()) }
    }

    /**
     * Reverse function.
     *
     * @return the function
     */
    @Bean
    open fun reverse(): Function<String, String> {
        return Function { value: String? -> StringBuilder(value).reverse().toString() }
    }

    companion object {
        /**
         * The entry point of application.
         *
         * @param args the input arguments
         */
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(GreeterApplication::class.java, *args)
        }
    }
}
