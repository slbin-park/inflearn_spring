package rest_example.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest_example.rest.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
