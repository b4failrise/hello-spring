package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        repository.save(member);
        // get으로 Optional 값을 바로 꺼내는 행위는 좋은 습관이 아니다.(테스트 코드에서는 상관없다)
        // isPresent() 를 사용하여 개선
        Member result = repository.findById(member.getId()).get();
//        System.out.println("result = " + (result == member));   // 검증 코드 (soutv)
        /* Assertions 를 이용한 테스트
        * 사용하는 목적 : assert는 release configuration 에선 컴파일 되지 않는다
        * => debug 모드에서만 동작하고, relase 모드에서는 동작하지 않기 때문에 프로그램을 중지시킬 일도 없다.
        * */
//        Assertions.assertEquals(member, result);    // 아무런 출력도 하지 않음. param(1): 비교하려는 입력값, param(2): 기대하는 결과
        assertThat(member).isEqualTo(result);    // assertj
    }
}
