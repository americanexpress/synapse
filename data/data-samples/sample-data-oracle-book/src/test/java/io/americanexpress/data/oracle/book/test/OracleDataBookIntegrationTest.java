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
package io.americanexpress.data.oracle.book.test;

import io.americanexpress.data.oracle.book.dao.BookRepository;
import io.americanexpress.data.oracle.book.test.config.OracleDataConfigTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@code OracleDataBookIntegrationTest} Integration test example of querying BookEntity by id.
 */
@Import(OracleDataConfigTest.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OracleDataBookIntegrationTest {

    @Autowired
    BookRepository bookRepository;


    @Test
    public void findByPublisher_givenId_expectedBookFound() {
        Assert.assertNotNull(bookRepository.findById(1));
    }

}
