package io.americanexpress.data.book;

import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.data.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class BookApplication implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting cassandra application");
        BookEntity book = new BookEntity("Alice In Wonderland", "Lewis Carroll");
        book.setIdentifier(UUID.randomUUID());
        bookRepository.save(book);
    }
}
