package rest_example.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest_example.rest.model.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
}
