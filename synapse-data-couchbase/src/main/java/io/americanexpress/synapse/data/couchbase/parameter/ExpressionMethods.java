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
package io.americanexpress.synapse.data.couchbase.parameter;

import com.couchbase.client.java.query.dsl.Expression;

import static com.couchbase.client.java.query.dsl.Expression.x;

/**
 * The type Expression methods.
 */
public class ExpressionMethods {

    /**
     * Equals expression.
     *
     * @param propertyKey the property key
     * @param key         the key
     * @return the expression
     */
    public static Expression equals(String propertyKey, String key) {
        return x(propertyKey).eq("$" + key);
    }

    /**
     * Not equals expression.
     *
     * @param propertyKey the property key
     * @param key         the key
     * @return the expression
     */
    public static Expression notEquals(String propertyKey, String key) {
        return x(propertyKey).ne("$" + key);
    }

    /**
     * Contains expression.
     *
     * @param propertyKey the property key
     * @param key         the key
     * @return the expression
     */
    public static Expression contains(String propertyKey, String key) {
        return x("CONTAINS(LOWER(" + propertyKey + "), LOWER($" + key + "))");
    }

    /**
     * In expression.
     *
     * @param propertyKey the property key
     * @param key         the key
     * @return the expression
     */
    public static Expression in(String propertyKey, String key) {
        return x(propertyKey).in("$" + key);
    }

    /**
     * Greater than or equals expression.
     *
     * @param propertyKey the property key
     * @param key         the key
     * @return the expression
     */
    public static Expression greaterThanOrEquals(String propertyKey, String key) {
        return x(propertyKey).gte("$" + key);
    }
       
    /**
     * Less than or equals expression.
     *
     * @param propertyKey the property key
     * @param key         the key
     * @return the expression
     */
    public static Expression lessThanOrEquals(String propertyKey, String key) {
        return x(propertyKey).lte("$" + key);
    }
    
    /**
     * Between started with min value.
     *
     * @param propertyKey the property key
     * @param key         the key
     * @return the expression
     */
    public static Expression betweenStart(String propertyKey, String key) {
        return x(propertyKey).between("$" + key);
    }
    
    /**
     * Between finished with max value.
     *
     * @param propertyKey the property key
     * @param key         the key
     * @return the expression
     */
    public static Expression betweenEnd(String propertyKey, String key) {
        return x("$" + key);
    }

    /**
     * Is null expression.
     *
     * @param propertyKey the property key
     * @param key         the key
     * @return the expression
     */
    public static Expression isNull(String propertyKey, String key) {
        return x(propertyKey + " IS NULL");
    }

    /**
     * Is null expression.
     *
     * @param propertyKey the property key
     * @param key         the key
     * @return the expression
     */
    public static Expression or(String propertyKey, String key) {
        return x(" OR ");
    }

    /**
     * Is not null expression.
     *
     * @param propertyKey the property key
     * @param key         the key
     * @return the expression
     */
    public static Expression isNotNull(String propertyKey, String key) {
        return x(propertyKey + " IS NOT NULL");
    }

    /**
     * Is missing expression.
     *
     * @param propertyKey the property key
     * @param key         the key
     * @return the expression
     */
    public static Expression isMissing(String propertyKey, String key) {
        return x(propertyKey + " IS MISSING");
    }

    /**
     * Is missing expression.
     *
     * @param propertyKey the property key
     * @param key         the key
     * @return the expression
     */
    public static Expression isNotMissing(String propertyKey, String key) {
        return x(propertyKey + " IS NOT MISSING");
    }
    
    /**
     * Is null or missing expression.
     *
     * @param propertyKey the property key
     * @param key         the key
     * @return the expression
     */
    public static Expression isNullOrMissing(String propertyKey, String key) {
        return x(propertyKey + " IS NULL OR " + propertyKey + " IS MISSING");
    }


}
