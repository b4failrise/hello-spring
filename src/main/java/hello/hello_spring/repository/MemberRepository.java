package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

/* 레포지토리 인터페이스 : 회원 레포지토리 인터페이스
* 필드는 public static final 제어자만, 메소드는 public abstract 제어자만 (생략) 적용된다.
* (인터페이스는 구현체 필요)
* */
public interface MemberRepository {
    /* 회원을 저장하는 함수 */
    Memmber save(Member member);


    /* id 기준으로 회원을 조회하는 함수
    * Optional : Null 날 것을 그대로 반환하기 보다 Optional로 한번 감싸서 반환함
    * (java8부터 지원하는 기능)
    * Optional 은 값이 있을 수도 있고 없을 수도 있는 경우(Null)를 안전하게 처리하기 위한 컨테이너
    */
    Optional<Member> findById(Long id);
    /* name 기준으로 회원을 조회하는 함수 */
    Optional<Member> findByName(String name);
    /* 모든 회원을 조회하는 함수 */
    List<Member> findAll();
}
