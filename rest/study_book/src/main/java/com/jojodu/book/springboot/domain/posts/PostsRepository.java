package com.jojodu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository <Entity 클래스 , PK 타입>
// 를 상속하면 기본적인 CRUD 메서드가 자동으로 생성이 된다.
// @Repository 를 추가할 필요가 없음
// 그리고 Entity 클래스와 기본 Entity Repository는 함께 위치해야함
public interface PostsRepository extends JpaRepository<Posts,Long> {
}
