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
package io.americanexpress.synapse.conventions;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

/**
 * ArchUnit guardrails that make Synapse conventions machine-enforceable, so AI-generated (and
 * human) modules fail fast with a precise message instead of compiling into a subtly-wrong shape.
 *
 * <p>Drop this into a Synapse application module's test sources, set {@link #APP_PACKAGE} to the
 * app's root package, and run {@code mvn test}. An agent runs it as its verify loop and self-corrects
 * from the failure messages.
 *
 * <p>Wiring (test scope):
 * <pre>{@code
 * <dependency>
 *   <groupId>com.tngtech.archunit</groupId>
 *   <artifactId>archunit-junit5</artifactId>
 *   <version>1.3.0</version>
 *   <scope>test</scope>
 * </dependency>
 * }</pre>
 *
 * NOTE: provided as a ready-to-use template under docs/agent — it is not yet wired into the reactor
 * build, so it has not been compiled in CI. See docs/agent/RECOMMENDATIONS.md (#4) for promoting it
 * into a shared {@code synapse-architecture-test} module.
 */
class SynapseConventionTest {

    /** Root package of the application module under test. */
    private static final String APP_PACKAGE = "com.example.app";

    private static final String CONTROLLER_BASE = "io.americanexpress.synapse.service.rest.controller.";
    private static final String SERVICE_BASE = "io.americanexpress.synapse.service.rest.service.";

    private final JavaClasses appClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages(APP_PACKAGE);

    @Test
    void restControllersExtendASynapseBaseController() {
        ArchRule rule = classes()
                .that().areAnnotatedWith("org.springframework.web.bind.annotation.RestController")
                .should().beAssignableTo(CONTROLLER_BASE + "BaseController")
                .because("Synapse REST endpoints must extend a Base*Controller so the verb mapping, "
                        + "validation, Swagger and exception handling are inherited");
        rule.check(appClasses);
    }

    @Test
    void controllersLiveInAControllerPackage() {
        ArchRule rule = classes()
                .that().areAssignableTo(CONTROLLER_BASE + "BaseController")
                .should().resideInAPackage("..controller..")
                .because("Synapse modules keep controllers in a 'controller' package");
        rule.check(appClasses);
    }

    @Test
    void controllersDoNotDeclareTheirOwnVerbMappings() {
        ArchRule rule = noClasses()
                .that().areAssignableTo(CONTROLLER_BASE + "BaseController")
                .should().beAnnotatedWith("org.springframework.web.bind.annotation.PostMapping")
                .orShould().beAnnotatedWith("org.springframework.web.bind.annotation.GetMapping")
                .orShould().beAnnotatedWith("org.springframework.web.bind.annotation.PutMapping")
                .orShould().beAnnotatedWith("org.springframework.web.bind.annotation.DeleteMapping")
                .because("the Base*Controller already maps the HTTP verb; concrete controllers only "
                        + "declare @RestController and the @RequestMapping base path");
        rule.check(appClasses);
    }

    @Test
    void servicesLiveInAServicePackage() {
        ArchRule rule = classes()
                .that().areAssignableTo(SERVICE_BASE + "BaseService")
                .should().resideInAPackage("..service..")
                .because("Synapse modules keep services in a 'service' package");
        rule.check(appClasses);
    }

    @Test
    void noHandwrittenExceptionHandlers() {
        ArchRule rule = noClasses()
                .that().resideInAPackage(APP_PACKAGE + "..")
                .should().beAnnotatedWith("org.springframework.web.bind.annotation.RestControllerAdvice")
                .because("Synapse auto-registers ControllerExceptionHandler via the layer config; a "
                        + "custom advice silently overrides the standard ErrorResponse contract");
        rule.check(appClasses);
    }

    @Test
    void entitiesExtendSynapseBaseEntity() {
        ArchRule rule = classes()
                .that().areAnnotatedWith("jakarta.persistence.Entity")
                .should().beAssignableTo("io.americanexpress.synapse.data.jpa.entity.BaseEntity")
                .because("entities inherit the id + audit columns from Synapse BaseEntity");
        rule.check(appClasses);
    }
}
