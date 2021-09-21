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
package io.americanexpress.synapse.utilities.common.date;

public enum DateFormat {

    MONTH_DAY_COMMA_YEAR("MMMM dd, yyyy"), ISO_DATE("yyyy-MM-dd"), ISO_TIME("yyyy-MM-dd HH:mm:ss"), ISO_INSTANT("yyyy-MM-dd'T'HH:mm:ssZ"), MONTH_DAY_YEAR_WITH_LEADING_ZEROS("mm/dd/yyyy");

    private String value;

    DateFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
