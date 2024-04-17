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
//import io.americanexpress.service.book.rest.model.CreateBookRequest;
//import io.americanexpress.service.book.rest.model.CreateBookResponse;
//import io.americanexpress.service.book.rest.service.CreateBookService;
//import io.americanexpress.synapse.service.test.controller.BaseCreateMonoControllerUnitTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//
///**
// * {@code CreateBookControllerTest} tests the {@link CreateBookController}
// */
//@SpringBootTest(classes = BookTestConfig.class, webEnvironment = WebEnvironment.MOCK)
//public class CreateBookControllerTest extends BaseCreateMonoControllerUnitTest<CreateBookResponse, CreateBookRequest, CreateBookService> {
//
//    /**
//     * Provide the endpoint for the api.
//     */
//    @Override
//    protected String getEndpoint() {
//        return BookEndpoints.BOOK_ENDPOINT;
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
//     * Provide the http headers needed.
//     */
//    @Override
//    protected HttpHeaders getSampleHttpHeaders() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Correlation-ID", "ghaof");
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        return headers;
//    }
//}
