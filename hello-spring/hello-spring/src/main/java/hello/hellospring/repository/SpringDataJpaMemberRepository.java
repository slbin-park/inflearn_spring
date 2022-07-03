package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// JpaRepository 이걸 사용하면 스프링 빈에 등록을 해줘서 사용이 가능한거임
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {


    //JPOL select m from Member m where m.name ?
    @Override
    Optional<Member> findByName(String name);
}