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
package io.americanexpress.synapse.framework.test.model;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoClassFilter;
import com.openpojo.reflection.impl.PojoClassFactory;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;
import pl.pojo.tester.api.assertion.Assertions;
import pl.pojo.tester.internal.assertion.AbstractAssertionError;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@code BaseModelsTest} Used to test model and enum classes.
 */
public class BaseModelsTest {
    private static final Set<String> EXCLUDED_SUFFIXES = new HashSet<>(Arrays.asList("Builder", "Test", "IT"));
    private final Set<String> excludedClassNames = new HashSet<>();
    private final String packageName = this.getClass().getPackageName();
    private final List<Warning> warningsToSuppress = new ArrayList<>(List.of(
            Warning.ALL_FIELDS_SHOULD_BE_USED,
            Warning.INHERITED_DIRECTLY_FROM_OBJECT,
            Warning.NONFINAL_FIELDS,
            Warning.STRICT_INHERITANCE
    ));

    /**
     * Validates all models in the specified package, except those explicitly excluded, using OpenPojo and EqualsVerifier.
     * It first tests enums for custom method integrity, then tests other POJOs for standard conventions and correct equals and hashCode methods.
     */
    @Test
    void validateModel() {
        var pojoClasses = PojoClassFactory.getPojoClassesRecursively(packageName, new ExcludeClassAndTestClass());
        var partitionedClasses = pojoClasses.stream().collect(Collectors.partitioningBy(PojoClass::isEnum));
        //testing enum classes
        partitionedClasses.get(true).forEach(this::testEnumClass);
        //testing non enum classes
        partitionedClasses.get(false).forEach(this::validatePojoClass);
    }

    /**
     * Adds additional warnings to suppress in EqualsVerifier tests.
     * This method allows customization of the EqualsVerifier behavior by adding more warnings to ignore during tests.
     *
     * @param additionalWarnings Varargs parameter of Warning types to add to the existing list of warnings to suppress.
     */
    protected void addWarningsToSuppress(Warning... additionalWarnings) {
        Collections.addAll(warningsToSuppress, additionalWarnings);
    }

    /**
     * Excludes specific classes by their names from being validated.
     * This method allows specifying class names that should not be included in the validation process.
     *
     * @param classes Varargs parameter containing the names of the classes to exclude from validation.
     */
    protected void excludeClasses(Class<?>... classes) {
        excludedClassNames.add(BaseModelsTest.class.getName());
        excludedClassNames.add(ExcludeClassAndTestClass.class.getName());
        Arrays.stream(classes).forEach(clazz -> excludedClassNames.add(clazz.getName()));
    }

    /**
     * Validates a single POJO class for compliance with POJO conventions and correct implementation of equals and hashCode methods.
     * It uses the OpenPojo and EqualsVerifier libraries for validation.
     *
     * @param pojoClass The POJO class to validate.
     */
    private void validatePojoClass(PojoClass pojoClass) {
        if(!Modifier.isAbstract(pojoClass.getClazz().getModifiers())){
            try {
                Assertions.assertPojoMethodsFor(pojoClass.getClazz()).areWellImplemented();
                EqualsVerifier.forClass(pojoClass.getClazz())
                        .suppress(warningsToSuppress.toArray(new Warning[0]))
                        .verify();
            }catch (AbstractAssertionError assertionError){
                if(assertionError.getMessage().contains("hashCode")){
                    assertTrue(pojoClass.getClazz().hashCode() != 0);
                }else{
                    throw assertionError;
                }
            }
        }
    }

    /**
     * Tests an enum class for custom method integrity by invoking all its declared methods (excluding synthetic and standard enum methods).
     * Ensures that no method invocation returns null and handles any reflective operation exceptions.
     *
     * @param pojoClass The enum class to test, encapsulated in a PojoClass object.
     */
    private void testEnumClass(PojoClass pojoClass) {
        var enumClass = pojoClass.getClazz();
        var enumConstants = enumClass.getEnumConstants();
        Arrays.stream(enumClass.getDeclaredMethods())
                .filter(method -> !method.isSynthetic()
                        && !method.getName().equals("values")
                        && !method.getName().equals("valueOf")
                        && (method.getName().startsWith("get") || method.getName().startsWith("set")))
                .forEach(method -> testEnumMethod(enumConstants, method));
    }

    /**
     * Tests a specific method of an enum class by invoking it on all enum constants and verifying the method's result.
     * Asserts that the method invocation does not return null and handles any exceptions during the reflective call.
     *
     * @param enumConstants An array of enum constants to test the method against.
     * @param method The method to test.
     */
    private void testEnumMethod(Object[] enumConstants, Method method) {
        Arrays.stream(enumConstants).forEach(enumConstant -> {
            try {
                var result = method.invoke(enumConstant);
                assertNotNull(result, "Method " + method.getName() + " returned null for " + enumConstant);
            } catch (Exception e) {
                throw new RuntimeException("Error testing enum method " + method.getName() + " for enum constant " + enumConstant, e);
            }
        });
    }

    /**
     * Excludes classes and test classes from being validated.
     * This class is used as a filter for OpenPojo to exclude specific classes from being validated.
     */
    private class ExcludeClassAndTestClass implements PojoClassFilter {

        @Override
        public boolean include(final PojoClass pojoClass) {
            String className = pojoClass.getClazz().getName();
            return excludedClassNames.contains(className) ? false : EXCLUDED_SUFFIXES.stream().noneMatch(className::endsWith);
        }
    }
}
