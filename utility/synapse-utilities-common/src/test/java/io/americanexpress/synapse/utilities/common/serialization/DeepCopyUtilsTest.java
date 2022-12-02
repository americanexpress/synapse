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

import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.synapse.framework.exception.ApplicationServerException;
import io.americanexpress.synapse.utilities.common.config.UtilitiesCommonConfig;
import io.americanexpress.synapse.utilities.common.serialization.model.AnotherPerson;
import io.americanexpress.synapse.utilities.common.serialization.model.Person;
import io.americanexpress.synapse.utilities.common.serialization.model.Phone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@code DeepCopyUtilsTest} tests the {@link DeepCopyUtils}.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UtilitiesCommonConfig.class})
class DeepCopyUtilsTest {

    private DeepCopyUtils deepCopyUtils;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void initialize() {
        deepCopyUtils = new DeepCopyUtils(objectMapper);
    }

    @Test
    void objectDeepCloner_givenConstructor_expectedSuccess() throws Exception {
        Constructor<DeepCopyUtils> constructor = DeepCopyUtils.class.getDeclaredConstructor(ObjectMapper.class);
        constructor.setAccessible(true);
        constructor.newInstance(new ObjectMapper());
        constructor.setAccessible(false);
    }

    @Test
    void clone_nullData() {
        Phone actual = (Phone) deepCopyUtils.clone(null, Phone.class);
        assertNull(actual, "Phone is not null.");
    }

    @Test
    void clone_nullOutputClassType() {
        assertThrows(IllegalArgumentException.class, () -> deepCopyUtils.clone(null, null));
    }

    @Test
    void clone_incompatibleClasses() {
        Phone phone = new Phone();
        phone.setProviderName("Acme Telecom");
        phone.setLineNumber("(123) 456-7890");
        assertThrows(ApplicationServerException.class, () -> deepCopyUtils.clone(phone, String.class));
    }

    @Test
    void clone_complexObject() {
        Phone phone = new Phone();
        phone.setProviderName("Acme Telecom");
        phone.setLineNumber("(123) 456-7890");
        Person person = new Person();
        person.setId(111);
        person.setName("John Doe");
        person.setPhone(phone);

        AnotherPerson anotherPerson = (AnotherPerson) deepCopyUtils.clone(person, AnotherPerson.class);
        assertEquals(person.getId(), anotherPerson.getId(), "IDs are not equal.");
        assertEquals(person.getName(), anotherPerson.getName(), "Names are not equal.");
        assertEquals(person.getPhone().getProviderName(), anotherPerson.getPhone().getProviderName(), "Phone provider names are not equal.");
        assertEquals(person.getPhone().getLineNumber(), anotherPerson.getPhone().getLineNumber(), "Phone line numbers are not equal.");
    }
}
