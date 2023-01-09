package com.prototype.classyBackEnd.member.controller;

import com.prototype.classyBackEnd.common.handler.ValidationHandler;
import com.prototype.classyBackEnd.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    MemberService memberService;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    ValidationHandler validationHandler;

    private final String testEmail = "testEmail@email.com";
    private final String testPassword = "testPassword";
    private final String testName = "testName";
    private final String testNickname = "testNickname";


}
