package rest_example.rest.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="author")
public class Author {

    @JsonBackReference
    // 이거순환참조를 방어하기위한 어노테이션임
    // 자식 클래스에 붙임
    @OneToMany(mappedBy = "author",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // 저자를 삭제하면 쓴 책을 같이 삭자헤가ㅣ위해
    private List<Book> books;

    @Id@GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
}
