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

import java.time.LocalDate;

public class SampleDeserializedObject {

    private String someName;

    private int someNumber;

    private SampleNestedObject sampleNestedObject;

    private LocalDate someLocalDate;

    public String getSomeName() {
        return someName;
    }

    public void setSomeName(String someName) {
        this.someName = someName;
    }

    public int getSomeNumber() {
        return someNumber;
    }

    public void setSomeNumber(int someNumber) {
        this.someNumber = someNumber;
    }

    public SampleNestedObject getSampleNestedObject() {
        return sampleNestedObject;
    }

    public void setSampleNestedObject(SampleNestedObject sampleNestedObject) {
        this.sampleNestedObject = sampleNestedObject;
    }

    public LocalDate getSomeLocalDate() {
        return someLocalDate;
    }

    public void setSomeLocalDate(LocalDate someLocalDate) {
        this.someLocalDate = someLocalDate;
    }
}
