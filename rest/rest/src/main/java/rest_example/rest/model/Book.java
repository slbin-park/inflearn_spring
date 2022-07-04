package rest_example.rest.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


// lombok -> Getter , Setter , ToString 같은 메소드
// 작업 관련 클래스 코드를 깜끔하게 작성할 수 있음
@Getter
@Setter
@Entity
@Table(name = " book")
// 매핑할 테이블을 지정하는것
public class Book {


    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonManagedReference
    private Author author;

    @Id
    // 해당 프로퍼티가 pk 역할을 한다는 것을 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // PK의 값을 위한 자동 생성 전략을 명시하는데 사용
    // generator 와 strategy가 있다
    // 디폴트 값은 AUTO 임
    // IDENTITY 전략은 AUTO_INCREMENT처럼 데이터베이스에
    // 값을 저장하고 나서 기본 키 값을 구할 수 있을 때 사용
    private Long id;
    private String name;
    private String isbn;


}
