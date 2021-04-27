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
package com.americanexpress.synapse.data.couchbase.builder;


import com.americanexpress.synapse.data.couchbase.parameter.ClauseType;
import com.americanexpress.synapse.data.couchbase.parameter.Parameter;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * The type Expression path.
 */
public class ExpressionPath extends BasePath {

    /**
     * Instantiates a new Expression path.
     *
     * @param other the other
     */
    ExpressionPath(BasePath other) {
        super(other);
    }

    /**
     * Only if non empty apply path.
     *
     * @return the apply path
     */
    public ApplyPath onlyIfNonEmpty() {
//        this.condition = StringUtils::isNotEmpty;
        return new ApplyPath(this);
    }

    /**
     * Only if apply path.
     *
     * @param condition the condition
     * @return the apply path
     */
    public ApplyPath onlyIf(Predicate<Object> condition) {
        this.condition = condition;
        return new ApplyPath(this);
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
     * Add a new parameter to the parameters collection that will connect with the previous expression with a Where clause if it is the first parameter or an And if not.
     */
    public void add() {
        parameters.add(new Parameter<String>(key, value, condition, expressionMethod, type));
    }

    /**
     * Add a new parameter to the parameters collection that connects with the previous expression with an OR clause.
     */
    public void addWithOrBefore() {
        parameters.add(new Parameter<String>(key, value, condition, expressionMethod, type, ClauseType.OR));
    }

}
