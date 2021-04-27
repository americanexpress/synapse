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

import com.americanexpress.synapse.framework.exception.ApplicationServerException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * DeepCopyUtils class is used to perform a deep copy of one object
 * into a possibly different object. For example, an object of class A with String field 'a'
 * can be deep copied into another object of class B with String field 'a'.
 * <p>
 * Nested objects, maps, collections and so forth can also be deep copied. You should primarily
 * use this class for this purpose. If the object model doesn't contain nested objects,
 * consider using Spring's BeanUtils.copyProperties().
 * <p>
 * If you need to perform a deep clone of objects of the same class,
 * please override Object.clone() in your object model.
 *
 * @author Paolo Claudio
 */
public class DeepCopyUtils {

    /**
     * Object mapper used for cloning.
     */
    private final ObjectMapper mapper;

    /**
     * <code>DeepCopyUtils</code> Constructor
     *
     * @param mapper used for mapping source and destination objects
     */
    public DeepCopyUtils(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Deep clone an object of the same object structure into another object.
     * The fields and nested objects must follow the same structure and naming in both classes.
     *
     * @param data            to be deep copied
     * @param outputClassType output type of the copied object
     * @return the cloned object
     */
    public <T> T clone(Object data, Class<T> outputClassType) {
        try {
            String serializedData = mapper.writeValueAsString(data);
            return mapper.readValue(serializedData, outputClassType);
        } catch (IOException exception) {
            //This one will be handled by the handlesApplicationException method first if, when cause != null, since cause is the exception that we pass here.
            throw new ApplicationServerException(exception);
        }
    }
}
