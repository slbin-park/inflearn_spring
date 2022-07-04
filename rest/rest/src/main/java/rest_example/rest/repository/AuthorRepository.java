package rest_example.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest_example.rest.model.Author;


/*
    JpaRepository 에 기본적으로 지원하는 메소드
    save() 레코드 저장
    findOne() PK로 레코드 한 건 찾기
    findAll() 전체 레코드 불러오기
    count() 레코드 갯수
    delete() 레코드 삭제
*/

public interface AuthorRepository extends JpaRepository<Author,Long> {


}
