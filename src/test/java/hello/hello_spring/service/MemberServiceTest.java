package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
// Test 클래스 생성 단축키 : 테스트를 하려는 클래스에서 ctrl+shift+T
class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    // Service 안에서 생성한 레포지토리와 Test 클래스에서 생성한 레포지토리는 다른 인스턴스임
    // store 저장소를 static으로 생성했기 때문에 인스턴스와 상관없이 클래스 레벨에서 동작됨. 크게 문제는 안 되지만 다른 레포지토리를 사용하고 있는 상황
    // 외부에서 넣어주도록(DI) 수정 -> 동일한 레포지토리를 사용
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member one = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(one.getName());
    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // 예외 검증
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // 메시지 검증
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member2);
//            fail();
//        } catch(IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}