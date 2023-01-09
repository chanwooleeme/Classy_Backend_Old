//package com.prototype.classyBackEnd;
//
//import com.prototype.classyBackEnd.member.domain.Member;
//import com.prototype.classyBackEnd.member.service.MemberService;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.sql.SQLException;
//
//@SpringBootTest
//public class MemberDaoTest {
//
//    @Autowired
//    MemberService memberService;
//
//    @Test
//    void MemberInsert(){
//        Member member = new Member();
//        member.setKakaoId(123123L);
//        member.setClassyIdName("kim2");
//        member.setClassyNickName("clas2");
//        memberService.save(member);
//    }
//
//    @Test
//    void findMember() throws SQLException {
//        Member member = memberService.findOneByClassyNickName("testlee");
//        Assertions.assertThat(member.getClassyNickName()).isEqualTo("testlee");
//    }
//}
