package io.americanexpress.data.book.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import redis.embedded.RedisServer;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Import({BookDataConfig.class})
@TestConfiguration
public class BookDataConfigTest {

    private RedisServer redisServer;

    public BookDataConfigTest(Environment environment) {
        this.redisServer = new RedisServer(Integer.parseInt(environment.getRequiredProperty("spring.redis.port")));
    }

    @PostConstruct
    public void postConstruct() {
        redisServer.start();
    }

    @PreDestroy
    public void preDestroy() {
        redisServer.stop();
    }
}
