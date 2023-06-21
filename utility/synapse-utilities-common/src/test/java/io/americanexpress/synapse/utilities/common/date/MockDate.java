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

import java.time.LocalDateTime;

/**
 * {@code MockDate} is used to mock a date for tests.
 */
public final class MockDate {

    public static final String EPOCH_MILLI = "1451024565000";

    public static final String ISO_DATE = "2015-12-25";

    private static final String ISO_TIME = "01:22:45";

    private static final String ISO_S = ".4";

    private static final String ISO_SS = ".45";

    private static final String ISO_SSS = ".452";

    public static final String ISO_DATE_TIME = ISO_DATE + " " + ISO_TIME; // // yyyy-MM-dd HH:mm:ss

    public static final String ISO_DATE_TIME_SS = ISO_DATE + " " + ISO_TIME + ISO_SS; // yyyy-MM-dd HH:mm:ss.SS

    public static final String ISO_DATE_TIME_SSS = ISO_DATE + " " + ISO_TIME + ISO_SSS; // yyyy-MM-dd HH:mm:ss.SSS

    public static final String ISO_LOCAL_DATE_TIME = ISO_DATE + "T" + ISO_TIME; // yyyy-MM-dd'T'HH:mm:ss

    public static final String ISO_LOCAL_DATE_TIME_S = ISO_DATE + "T" + ISO_TIME + ISO_S; // yyyy-MM-dd'T'HH:mm:ss.S

    public static final String ISO_LOCAL_DATE_TIME_SS = ISO_DATE + "T" + ISO_TIME + ISO_SS; // yyyy-MM-dd'T'HH:mm:ss.SS

    public static final String ISO_LOCAL_DATE_TIME_SSS = ISO_DATE + "T" + ISO_TIME + ISO_SSS; // yyyy-MM-dd'T'HH:mm:ss.SSS

    public static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.parse(ISO_LOCAL_DATE_TIME_SSS);

    private MockDate() {
    }
}
