package io.americanexpress.data.mysql.book.test.config;

import io.americanexpress.data.mysql.book.config.BookDataConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BookDataConfig.class)
public class MySqlDataConfigTest {

}
