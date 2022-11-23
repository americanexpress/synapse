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
package io.americanexpress.synapse.utilities.common.miscellaneous;


/**
 * {@code GetValueOrDefault} contains method for get value if value passed if not null.
 */
public class GetValueOrDefault {

    /**
     * Generic helper method to return the default value passed if the value passed is null.
     *
     * @param value        the value to be null checked
     * @param defaultValue the default value to be returned if value is null
     * @param <T>          generic type T
     * @return the type
     */
    public static <T> T get(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

}
