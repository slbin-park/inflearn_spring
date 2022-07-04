package rest_example.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest_example.rest.model.Book;
import rest_example.rest.model.Lend;
import rest_example.rest.model.LendStatus;

import java.util.Optional;

public interface LendRepository extends JpaRepository<Lend, Long> {
    Optional<Lend> findByBookAndStatus(Book book, LendStatus status);
}
