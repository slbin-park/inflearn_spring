package com.jojodu.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
// 기본 생성자 자동 추가
@Entity
// 테이블과 링크될 클래스임을 나타냄
// 기본값으로 카멜케이스 으름은 _으로 테이블 이름을 매칭
// SalesManager -> slaes_manager
public class Posts {

    @Id
    // PK필드임
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // PK 생성 규칙
    // IDENTITY를 붙이면 auto_increment가 된다.
    private Long id;

    @Column(length = 500, nullable = false)
    // 이걸 사용안해도 클래스 필드느는 모두 컬럼이 된다.
    // 사용하는 이유는? 변경이 필요한 옵션이 있을 경우 사용한다.
    private String title;

    @Column(columnDefinition = "Text", nullable = false)
    private String content;

    private String author;

    @Builder
    // 해당 클래스의 빌더 패턴 클래스를 생성
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
