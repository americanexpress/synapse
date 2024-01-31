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
package io.americanexpress.synapse.data.db2.repository;

import io.americanexpress.synapse.data.db2.entity.BaseDb2Entity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * {@code BaseCrudDb2Repository} contains the various database operations which can be expanded per use case.
 * @param <E> the specific entity implementation for the user's DB2 instance
 * @param <ID> the type of the id column
 *
 * @author tisla4
 */
@NoRepositoryBean
public interface BaseCrudDb2Repository<E extends BaseDb2Entity, ID> extends CrudRepository<E, ID> {

}
