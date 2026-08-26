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
package io.americanexpress.synapse.utilities.common.model;

import java.util.Collection;

public class SampleNestedCollectionsObject {

    private Collection<String> someStringCollection;

    private String[] someStringArray;

    public void setSomeStringCollection(Collection<String> someStringCollection) {
        this.someStringCollection = someStringCollection;
    }

    public void setSomeStringArray(String[] someStringArray) {
        this.someStringArray = someStringArray;
    }

    public Collection<String> getSomeStringCollection() {
        return someStringCollection;
    }

    public String[] getSomeStringArray() {
        return someStringArray;
    }
}
