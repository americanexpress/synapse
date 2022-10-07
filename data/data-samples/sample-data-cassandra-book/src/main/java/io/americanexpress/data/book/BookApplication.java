package io.americanexpress.data.book;

import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.data.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.print.Book;
import java.util.List;
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

        BookEntity bookEntity = bookRepository.findByTitleAndAuthor("Alice In Wonderland", "Lewis Caroll");
        bookEntity.setCreatedBy("whu16");
        bookEntity.setLastModifiedBy("whu16");
        bookRepository.save(bookEntity);

        List<BookEntity> bookEntityList = bookRepository.findAll();
        bookEntityList.forEach(book -> {
            System.out.println("Book " + book.getTitle() + " Author " + book.getAuthor());
        });
    }
}
