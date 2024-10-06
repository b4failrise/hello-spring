package hello.hello_spring.domain;

public class Member {
    private Long id;    // 고객이 정하는 id가 아닌 시스템이 정하는 seq 값
    private String name;

    // alt + insert : getter, setter 생성 단축키
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
