//package com.prototype.classyBackEnd.member.repository;
//
//import com.prototype.classyBackEnd.video.repository.MemberDao;
//import com.prototype.classyBackEnd.member.domain.Member;
//import com.prototype.classyBackEnd.member.repository.MemberRepository;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.sql.SQLException;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//public class MemberRepositoryTest {
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    MemberDao memberDao;
//
//    @Test
//    void 레포_테스트() throws SQLException {
//        String email = "testmail@mail.com";
//        Member member = new Member();
//        member.setMemberId(2L);
//        member.setEmail("testEmail@mail.com");
//        member.setClassyIdName("testId");
//        member.setPassword("testPassword");
//        member.setClassyNickName("testNickname");
//        Optional<Member> saveMember = memberDao.save(member);
//        String findEmail = saveMember.get().getEmail();
//        assertThat(findEmail).isEqualTo(email);
//    }
//
//    @Test
//    void SpringJpaTest() {
//        String email = "testmail@mail.com";
//        Member member = new Member();
//        member.setMemberId(1L);
//        member.setEmail("testEmail@mail.com");
//        member.setClassyIdName("testId");
//        member.setPassword("testPassword");
//        member.setClassyNickName("testNickname");
//        memberRepository.save(member);
//    }
//}
