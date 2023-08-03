package io.americanexpress.data.oracle.book.dao;

import io.americanexpress.data.oracle.book.entity.BookSPEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.ResultSet;

public interface BookSPRepository extends R2dbcRepository<BookSPEntity, Long> {

//    @Procedure("book_package.get_book_data")
    @Query(value = "{ ? = call get_book_data() }")
    Flux<BookSPEntity> getBookData();

    //    @Procedure("book_package.get_book_by_title_and_author")
    @Query("""
            CALL book_package.get_book_by_title_and_author(
            :p_title,
            :p_author,
            :p_book_data            
            )
            """)
    Mono<BookSPEntity> getBookByTitleAndAuthor(@Param("p_title") String title,
                                               @Param("p_author") String author,
                                               @Param("p_book_data") ResultSet[] bookData);

    //    @Procedure("book_package.get_book_by_title")
    @Query("BEGIN book_package.get_book_by_title(:p_title); END;")
    Mono<BookSPEntity> getBookByTitle(@Param("title") String title);

    //    @Procedure("book_package.update_book_title")
    @Query("CALL book_package.update_book_title(:p_new_title)")
    Mono<Void> updateBookTitle(@Param("p_newTitle") String newTitle);

    //    @Procedure("book_package.delete_books_by_title")
    @Query("CALL book_package.delete_book_by_title(:p_title)")
    Mono<Void> deleteBookByTitle(@Param("title") String title);

    //    @Procedure("book_package.insert_book")
    @Query("""
            CALL book_package.insert_book(
            :p_title,
            :p_author,
            :p_created_by,
            :p_last_modified_by
            )
            """)
    Mono<Void> insertBook(@Param("title") String title,
                          @Param("p_author") String author,
                          @Param("p_created_by") String createdBy,
                          @Param("p_last_modified_by") String lastModifiedBy);
}
