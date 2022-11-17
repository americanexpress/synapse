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
package io.americanexpress.synapse.utilities.common.io;

import org.springframework.util.PropertyPlaceholderHelper;

import java.util.Properties;

/**
 * {@code StringPlaceholderResolver} class resolves property placeholders
 * on a string. Property placeholders of the form <code>${name}</code> will be resolved
 * based on the values set in the {@link Properties}.

 * Example: suppose that the file named {@code myFile.txt} contains the text {@code My first name is ${firstName} and my last name is ${lastName}.}
 *
 * Then, to replace the property placeholders <code>firstName</code> and <code>lastName</code>, create a {@code Properties} object with new values.
 * {@code Properties properties = new Properties();
 * properties.put("firstName", "Tony");
 * properties.put("lastName", "Stark");}

 * Then, call the {@link StringPlaceholderResolver#resolve(Properties)} method to replace the values.<br>
 * {@code StringPlaceholderResolver stringPlaceholderResolver = new StringPlaceholderResolver("myFile.txt");<br>
 * String resolvedText = stringPlaceholderResolver.resolve(properties);}

 * The {@code resolvedText} would result as {@code My first name is Tony and my last name is Stark.}
 *
 */
public class StringPlaceholderResolver {

    /**
     * Default placeholder text which can be reused instead of reading from a file multiple times.
     */
    private final String placeholderText;

    /**
     * Used to resolve the property placeholders.
     */
    private final PropertyPlaceholderHelper propertyPlaceholderHelper;

    /**
     * Argument constructor creates a new instance of StringPlaceholderResolver with given values.
     * @param fileName containing the placeholder text to be loaded
     */
    public StringPlaceholderResolver(String fileName) {
        this.placeholderText = IOUtils.readFileToAString(fileName);
        this.propertyPlaceholderHelper = new PropertyPlaceholderHelper("${", "}");
    }

    /**
     * <p>Resolve the property placeholders using the values in the properties.
     * Each element of the properties object is a key/value pair
     * where the key corresponds to the placeholder property name
     * and the value corresponds to the placeholder property value.</p>
     *
     * <p>
     * Example:<br>
     * {@code
     * Properties properties = new Properties();<br>
     * // placeholder property key = "firstName", placeholder property value = "Tony"<br>
     * properties.put("firstName", "Tony");
     * }
     * </p>
     * @param properties containing the values to replace the property placeholders
     * @return the resolved string
     */
    public String resolve(Properties properties) {
        // Note: if properties is null or contains null values, allow the runtime exception to be thrown
        //       to immediately stop program execution
        return propertyPlaceholderHelper.replacePlaceholders(placeholderText, properties);
    }
}
