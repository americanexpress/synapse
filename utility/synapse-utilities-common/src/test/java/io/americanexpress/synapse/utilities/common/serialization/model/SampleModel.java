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
package io.americanexpress.synapse.utilities.common.serialization.model;

import io.americanexpress.synapse.utilities.common.serialization.AllCharactersMaskStringSerializer;
import io.americanexpress.synapse.utilities.common.serialization.CurrencySerializer;
import io.americanexpress.synapse.utilities.common.serialization.DecimalSerializer;
import io.americanexpress.synapse.utilities.common.serialization.FixedLengthMaskBigDecimalSerializer;
import io.americanexpress.synapse.utilities.common.serialization.Instant2LocalDateTimeStringSerializer;
import io.americanexpress.synapse.utilities.common.serialization.InstantSerializer;
import io.americanexpress.synapse.utilities.common.serialization.IntegerSerializer;
import io.americanexpress.synapse.utilities.common.serialization.LastFourCharactersMaskStringSerializer;
import io.americanexpress.synapse.utilities.common.serialization.LowerCaseStringSerializer;
import io.americanexpress.synapse.utilities.common.serialization.NationalIdentifierStringSerializer;
import io.americanexpress.synapse.utilities.common.serialization.PercentSerializer;
import io.americanexpress.synapse.utilities.common.serialization.PostalCodeStringSerializer;
import io.americanexpress.synapse.utilities.common.serialization.TelephoneStringSerializer;
import io.americanexpress.synapse.utilities.common.serialization.TitleCaseStringListSerializer;
import io.americanexpress.synapse.utilities.common.serialization.TitleCaseStringSerializer;
import io.americanexpress.synapse.utilities.common.serialization.UnitedStatesAbbreviationStringSerializer;
import io.americanexpress.synapse.utilities.common.serialization.UpperCaseStringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class SampleModel {

    @JsonSerialize(using = UpperCaseStringSerializer.class)
    private String firstName;

    @JsonSerialize(using = LowerCaseStringSerializer.class)
    private String lastName;

    @JsonSerialize(using = Instant2LocalDateTimeStringSerializer.class)
    private String birthDate;

    @JsonSerialize(using = PostalCodeStringSerializer.class)
    private String zipCode;

    @JsonSerialize(using = UnitedStatesAbbreviationStringSerializer.class)
    private String unitedState;

    @JsonSerialize(using = TitleCaseStringSerializer.class)
    private String fullName;

    @JsonSerialize(using = TitleCaseStringListSerializer.class)
    private List<String> words;

    @JsonSerialize(using = TelephoneStringSerializer.class)
    private String phoneNumber;

    @JsonSerialize(using = LastFourCharactersMaskStringSerializer.class)
    private String maskingNumbers;

    @JsonSerialize(using = AllCharactersMaskStringSerializer.class)
    private String allNumbersMasking;

    @JsonSerialize(using = NationalIdentifierStringSerializer.class)
    private String nationalIdentifier;

    @JsonSerialize(using = FixedLengthMaskBigDecimalSerializer.class)
    private BigDecimal balanceMasking;

    @JsonSerialize(using = DecimalSerializer.class)
    private Double totalRevenue;

    @JsonSerialize(using = IntegerSerializer.class)
    private Integer numbers;

    @JsonSerialize(using = CurrencySerializer.class)
    private BigDecimal currencyAmount;

    @JsonSerialize(using = PercentSerializer.class)
    private Double odds;

    @JsonSerialize(using = InstantSerializer.class)
    private Instant currentDateTime;

    public SampleModel() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getUnitedState() {
        return unitedState;
    }

    public void setUnitedState(String unitedState) {
        this.unitedState = unitedState;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMaskingNumbers() {
        return maskingNumbers;
    }

    public void setMaskingNumbers(String maskingNumbers) {
        this.maskingNumbers = maskingNumbers;
    }

    public String getAllNumbersMasking() {
        return allNumbersMasking;
    }

    public void setAllNumbersMasking(String allNumbersMasking) {
        this.allNumbersMasking = allNumbersMasking;
    }

    public String getNationalIdentifier() {
        return nationalIdentifier;
    }

    public void setNationalIdentifier(String nationalIdentifier) {
        this.nationalIdentifier = nationalIdentifier;
    }

    public BigDecimal getBalanceMasking() {
        return balanceMasking;
    }

    public void setBalanceMasking(BigDecimal balanceMasking) {
        this.balanceMasking = balanceMasking;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public BigDecimal getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(BigDecimal currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public Double getOdds() {
        return odds;
    }

    public void setOdds(Double odds) {
        this.odds = odds;
    }

    public Instant getCurrentDateTime() { return currentDateTime; }

    public void setCurrentDateTime(Instant currentDateTime) { this.currentDateTime = currentDateTime; }
}
