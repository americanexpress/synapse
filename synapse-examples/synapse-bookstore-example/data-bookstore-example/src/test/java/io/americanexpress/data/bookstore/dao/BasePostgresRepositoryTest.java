package io.americanexpress.data.bookstore.dao;

import io.americanexpress.synapse.framework.test.BaseTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class BasePostgresRepositoryTest extends BaseTest {
}
