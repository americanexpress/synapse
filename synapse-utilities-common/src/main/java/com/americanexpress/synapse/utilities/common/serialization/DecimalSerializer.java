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
package com.americanexpress.synapse.utilities.common.serialization;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * DecimalSerializer class formats all Doubles to include commas and limits to 2 digits after decimal.
 * e.g. if the text is 1234.1223323 then it will be serialized as 1,234.12
 *
 */
public class DecimalSerializer extends DoubleSerializer {

    /**
     * Used to format decimal numbers.
     */
    private static final String DECIMAL_FORMAT = "#,##0.00";

    /**
     * Set the number format as the default locale's decimal formatter.
     */
    @Override
    protected void setNumberFormat() {
        numberFormat = new DecimalFormat(DECIMAL_FORMAT, new DecimalFormatSymbols(Locale.getDefault()));
    }
}
