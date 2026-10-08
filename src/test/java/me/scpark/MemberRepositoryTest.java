package me.scpark;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

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


}