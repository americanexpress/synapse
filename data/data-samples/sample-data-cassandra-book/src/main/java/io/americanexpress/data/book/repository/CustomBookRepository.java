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
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.stereotype.Repository;

/**
 * {@code CustomBookRepository} is the custom repository to handle the queries for the books table.
 *
 * @author brenoreis
 */
@Repository
public class CustomBookRepository extends BaseCustomCassandraRepository<BookEntity> {

    /**
     * The environment.
     */
    private final Environment environment;

    /**
     * Creates a new instance of {@code CustomBookRepository} given a Cassandra template and time to live.
     *
     * @param cassandraTemplate the Cassandra template.
     * @param environment the environment.
     */
    protected CustomBookRepository(CassandraTemplate cassandraTemplate, Environment environment) {
        super(cassandraTemplate);
        this.environment = environment;
    }

    /**
     * Gets the insert options for the entity.
     *
     * @return the insert options.
     */
    @Override
    public InsertOptions getInsertOptions() {
        return InsertOptions.builder().ttl(environment.getProperty("book.time-to-live", Integer.class, 3)).build();
    }
}
