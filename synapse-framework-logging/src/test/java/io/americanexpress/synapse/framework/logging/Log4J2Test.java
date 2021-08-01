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
package io.americanexpress.synapse.framework.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.junit.jupiter.api.Test;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Log4J2Test {

    private final XLogger logger = XLoggerFactory.getXLogger(getClass());

    @Test
    void info_givenValidLogMessage_expectedFormattedLogMessage() {

        // Get the LoggerContext containing the configured logging format from log4j2-test.xml
        String expected = "%d{DEFAULT} %X{SEVERITY_LEVEL} %highlight{%5p}{FATAL=red blink, ERROR=red, WARN=yellow bold, INFO=white, DEBUG=green bold, TRACE=blue bold} %style{%01.15t}{magenta} %style{%-30.40C{1.}.%M %L}{cyan} - %msg %n";
        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
        String actual = loggerContext.getLogger("Log4J2Test")
                .getAppenders()
                .get("Console")
                .getLayout()
                .toString();
        logger.info("Sample Log Message");
        assertEquals(expected, actual);
    }
}
