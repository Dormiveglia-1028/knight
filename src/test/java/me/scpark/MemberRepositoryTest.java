package me.scpark;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Sql("/insert-members.sql")
    @DisplayName("MemberRepository를 통해 member 테이불의 모든 레코드(3개) 가져오기.")
    @Test
    public void getAllMembers() {
        //zhun bei given

        //shi xing when
        List<Member> member = memberRepository.findAll();

        // then
        assertThat(member.size()).isEqualTo(3);
    }

    @Sql("/insert-members.sql")
    @DisplayName("MemberRepository를 통해 이름이 'C'인 레코드 가져오기")
    @Test
    public void getMemberByName() {


        Optional<Member> optMember = memberRepository.findByName("C");
        Member member = optMember.get();

        assertThat(member.getId()).isEqualTo(3);
    }
    @Sql("/insert-members.sql")
    @DisplayName("MemberRepository를 통해 Id이 2인 레코드 가져오기")
    @Test
    public void getMemberById() {


        Member member = memberRepository.findById(2L).get();

        assertThat(member.getName()).isEqualTo("B");
    }

    @DisplayName("MemberRepository를 통해 이름이'펑하오'인 레코드 추가하기")
    @Test
    public void saveMember() {

        Member member = new Member("펑하오");


        memberRepository.save(member);


        List<Member> result = memberRepository.findAll();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("펑하오");
    }


}