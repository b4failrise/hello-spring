package hello.hello_spring.repository;


import hello.hello_spring.domain.Member;

import java.util.*;

/* 구현체 클래스
* 구현체 클래스에서 implements 키워드 뒤에 상속하는 인터페이스를 지정한다.
ctrl + O : 상속받는 함수를 자동 불러오기
 */


public class MemoryMemberRepository implements MemberRepository {
    /* Member 객체를 관리하는 간단한 저장소
    * 공유되는 변수이므로 동시성 문제가 있을 수 있기 때문에 ConcurrentHashMap 사용이 적절
    * Long 타입의 키와 Member 객체를 값으로 가지는 HashMap
    * private static으로 선언되어 있어 이 클래스의 인스턴스와 상관없이 하나의 stor만 존재
    * 즉, 모든 인스턴스가 같은 저장소를 공유함
    */
    private static Map<Long, Member> store = new HashMap<>();
    // 공유되는 변수이므로 동시성 문제가 있을 수 있기 때문에 AtomicLong 사용이 더 적절
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
//        return null;
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
//        return Optional.empty();
//        return store.get(id);
        return Optional.ofNullable(store.get(id));  // null이 반환될 여지가 있을 경우 Optional 로 감싸서 반환
    }
    /* name 기준으로 회원을 조회하는 함수
    * store.values() 를 사용하여 저장소에 있는 모든 값(Member 객체)를 가져온다.
    * stream() 을 사용하여 이 컬렉션을 스트림으로 변환
    * filter 메소드를 사용하여 member의 이름이 주어진 name과 같은 객체만 남긴다
    * findAny 는 필터링된 결과에서 아무 하나의 Member 객체를 찾고, 그것을 Optional 객체로 반환
    * 만약 해당 이름을 가진 Member 객체가 없으면 Optional.empty() 를 반환
    * */
    @Override
    public Optional<Member> findByName(String name) {
//        return Optional.empty();
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
//        return null;
        return new ArrayList<>(store.values());
    }
}
