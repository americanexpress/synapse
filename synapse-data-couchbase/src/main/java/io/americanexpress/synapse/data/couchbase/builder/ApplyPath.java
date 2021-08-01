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
package io.americanexpress.synapse.data.couchbase.builder;


import io.americanexpress.synapse.data.couchbase.parameter.Parameter;

import java.util.function.Function;

/**
 * The type Apply path.
 *
 * @author Alexei Morgado
 */
public class ApplyPath extends BasePath {

    /**
     * Instantiates a new Apply path.
     *
     * @param basePath the base path
     */
    public ApplyPath(BasePath basePath) {
        super(basePath);
    }

    /**
     * And apply.
     *
     * @param <T>    the type parameter
     * @param parser the parser
     */
    public <T> void andApply(Function<Object, T> parser) {
        parameters.add(new Parameter<T>(key, value, condition, expressionMethod, parser, type));
    }

    /**
     * Add.
     */
    public void add() {
        parameters.add(new Parameter<String>(key, value, condition, expressionMethod, type));
    }

}
