///*
// * Copyright 2020 American Express Travel Related Services Company, Inc.
// *
// * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
// * in compliance with the License. You may obtain a copy of the License at
// *
// * http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software distributed under the License
// * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
// * or implied. See the License for the specific language governing permissions and limitations under
// * the License.
// */
//package io.americanexpress.service.book.rest.controller;
//
//import io.americanexpress.service.book.rest.config.BookEndpoints;
//import io.americanexpress.service.book.rest.config.BookTestConfig;
//import io.americanexpress.service.book.rest.model.ReadBookRequest;
//import io.americanexpress.service.book.rest.model.ReadBookResponse;
//import io.americanexpress.service.book.rest.service.ReadBookService;
//import io.americanexpress.synapse.service.test.controller.BaseReadMonoControllerUnitTest;
//import org.springframework.http.HttpHeaders;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.web.WebAppConfiguration;
//
///**
// * {@code ReadMonoControllerTest} tests the {@link ReadMonoControllerTest}
// */
//@WebAppConfiguration
//@ContextConfiguration(classes = BookTestConfig.class)
//public class ReadMonoControllerTest extends BaseReadMonoControllerUnitTest<ReadBookResponse, ReadBookRequest, ReadBookService> {
//
//    /**
//     * Provide the endpoint for the api.
//     */
//    @Override
//    protected String getEndpoint() {
//        return BookEndpoints.BOOK_ENDPOINT + "/inquiry_results";
//    }
//
//    /**
//     * Provide the location of the sample response json file.
//     */
//    @Override
//    protected String getSampleJsonResponseFileName() {
//        return "sample-response.json";
//    }
//
//    /**
//     * Provide the location of the sample request json file.
//     */
//    @Override
//    protected String getSampleJsonRequestFileName() {
//        return "sample-request.json";
//    }
//
//    /**
//     * Provide any headers needed.
//     */
//    @Override
//    protected HttpHeaders getSampleHttpHeaders() {
//        return null;
//    }
//}
