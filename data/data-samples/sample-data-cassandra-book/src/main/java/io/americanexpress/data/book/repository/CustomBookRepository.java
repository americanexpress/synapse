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
package io.americanexpress.data.book.repository;

import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.synapse.data.cassandra.repository.BaseCustomCassandraRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;

/**
 * {@code CustomBookRepository} is the custom repository to handle the queries for the books table.
 *
 * @author brenoreis
 */
@Repository
public class CustomBookRepository extends BaseCustomCassandraRepository<BookEntity> {

    /**
     * Creates a new instance of {@code CustomBookRepository} given a Cassandra template and time to live.
     *
     * @param cassandraTemplate the Cassandra template.
     * @param timeToLive        the time to live.
     */
    protected CustomBookRepository(CassandraTemplate cassandraTemplate, @Qualifier("bookTimeToLive") int timeToLive) {
        super(cassandraTemplate, timeToLive);
    }
}
