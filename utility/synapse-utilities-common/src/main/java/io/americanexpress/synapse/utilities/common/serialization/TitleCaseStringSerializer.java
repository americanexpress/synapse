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
package io.americanexpress.synapse.utilities.common.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * {@code TitleCaseStringSerializer} class serializes a string into its title case format.
 * e.g. JOHN X DOE will be converted into John X Doe.
 * e.g. JANE-DOE O'REILLEY will be converted into Jane-Doe O'Reilly.
 * e.g. A.J. COWLING will be converted into A.J. Cowling.
 *
 * @author Paolo Claudio
 */
public class TitleCaseStringSerializer extends StringSerializer {

    /**
     * Serialize this text into title case.
     * e.g. JOHN X DOE will be converted into John X Doe.
     * e.g. JANE-DOE O'REILLEY will be converted into Jane-Doe O'Reilly.
     * e.g. A.J. COWLING will be converted into A.J. Cowling.
     *
     * @param text name to be converted
     * @return the title cased conversion of this name
     */
    public String serialize(String text) {

        String titleCasedText = text;

        if (text != null) {

            // Ensure that only 1 single space exists between each name and make the full name lowercase
            // so that we can make only the first letter of each name uppercase
            // e.g. "  JOHN   X   DOE  " --> "john x doe" in this step
            String fullName = text.trim().replaceAll(" +", " ").toLowerCase();
            char[] fullNameCharacters = fullName.toCharArray();

            // Make the first character uppercase, delimited by the 1 single space
            char currentLetter;
            char previousLetter;
            for (int i = 0; i < fullNameCharacters.length; i++) {
                currentLetter = fullNameCharacters[i];

                // If this is the first character in the fullNameCharacters array
                // or if the previous character was the 1 single space,
                // then this character should be uppercase
                //
                // e.g. if fullNameCharacters = "john x doe",
                //      then the first character fullNameCharacters[0] = 'j' should be converted to 'J'
                if (i == 0) {

                    // Make this character uppercase and reset in the fullNameCharactersArray
                    currentLetter = Character.toUpperCase(currentLetter);
                    fullNameCharacters[i] = currentLetter;
                } else {
                    previousLetter = fullNameCharacters[i - 1];

                    // If the previous character is the single 1 space previousLetter = ' '
                    // then this current character should be made uppercase
                    // e.g. the previous character of 'x' is ' ' so 'x' --> 'X'
                    // and the previous character of 'd' is ' ' so 'd' --> 'D'
                    //
                    // Also, if the previous character is a hyphen or apostrophe previousLetter = '-' || previousLetter = '\''
                    // then this current character should be made uppercase
                    // e.g. for hyphenated names "anne-marie", then the previous character of 'm' = '-' so 'm' --> 'M'
                    // e.g. for names with periods "j.k. rowling", the previous character of 'k' = '.' so 'k' --> 'K'
                    if (previousLetter == ' ' || previousLetter == '-' || previousLetter == '.') {

                        // Make this character uppercase and reset in the fullNameCharactersArray
                        currentLetter = Character.toUpperCase(currentLetter);
                        fullNameCharacters[i] = currentLetter;
                    }
                }
            }

            titleCasedText = new String(fullNameCharacters);
        }

        return titleCasedText;
    }

    /**
     * Convert the text into title case format.
     *
     * @param text      to be converted
     * @param generator JSON generator
     * @param provider  serializer provider
     * @throws IOException whenever there's an issue performing the serialization
     */
    @Override
    public void serialize(String text, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeObject(serialize(text));
    }
}
