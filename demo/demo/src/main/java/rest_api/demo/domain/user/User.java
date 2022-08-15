package rest_api.demo.domain.user;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class UserRepository {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String password;

    private String gender;

    @Builder
    public 
}
