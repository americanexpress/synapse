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
package com.americanexpress.synapse.framework.test.model;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoClassFilter;
import com.openpojo.reflection.filters.FilterEnum;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.BusinessKeyMustExistRule;
import com.openpojo.validation.rule.impl.EqualsAndHashCodeMatchRule;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.BusinessIdentityTester;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import com.openpojo.validation.test.impl.ToStringTester;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BaseModelsTest {
    protected String packageName = this.getClass().getPackage().getName();
    protected PojoClassFilter filterTestClasses = new FilterTestClasses();
    protected FilterEnum filterEnum = new FilterEnum();
    protected FilterBasedOnInheritanceTestClasses filterBasedOnInheritanceTestClasses = new FilterBasedOnInheritanceTestClasses();
    protected static Validator validator;

    @BeforeAll
    public static void initialize() {

        // Create the POJO validator
        validator = ValidatorBuilder.create()
                // Add the POJO validation rules for get/set methods, hashCode(), equals() and toString()
                .with(new SetterMustExistRule(),
                        new GetterMustExistRule(),
                        new EqualsAndHashCodeMatchRule(),
                        new BusinessKeyMustExistRule())
                // Add the POJO testes for get/set methods, hashCode(), equals() and toString()
                .with(new SetterTester(),
                        new GetterTester(),
                        new ToStringTester(),
                        new BusinessIdentityTester())
                // Build the POJO validator
                .build();
    }

    @Test
    protected void validateSettersAndGetters() {
        validator.validateRecursively(packageName, filterTestClasses, filterBasedOnInheritanceTestClasses, filterEnum);
    }

    // FilterTestClasses enables you to get all classes that are not of type test.
    private static class FilterTestClasses implements PojoClassFilter {
        @Override
        public boolean include(PojoClass pojoClass) {
            return !pojoClass.getSourcePath().contains("test");
        }
    }

    // This filter enables you to get all classes that implement or extend a given class.
    // For Example, get all classes that implement or extend ArrayList.
    private static class FilterBasedOnInheritanceTestClasses implements PojoClassFilter {
        @Override
        public boolean include(PojoClass pojoClass) {
            if (pojoClass.extendz(ArrayList.class)) {
                return false;
            }
            return true;
        }
    }

    protected static class FilterBasedOnSubClassesCondition implements PojoClassFilter {
        @Override
        public boolean include(PojoClass pojoClass) {
            if (pojoClass.getName().contains("ProductFilterCode") || pojoClass.getName().contains("ValidSpendServiceRequest")) {
                return false;
            }
            return true;
        }
    }
}
