package com.prototype.classyBackEnd.common.mail;

import com.prototype.classyBackEnd.common.handler.MailHandler;
import com.prototype.classyBackEnd.common.mail.repository.EmailVerifyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class MailAuthentication {

    private final MailHandler mailHandler;
    private final SpringTemplateEngine templateEngine;
    private final EmailVerifyRepository emailVerifyRepository;
    private Integer authNumber = 0;

    @Value("{spring.mail.username}")
    private String classyEmail;

    @Async
    public Integer join(String email) {
        try{
            makeRandomNumber();
            emailVerifyRepository.save(email, authNumber);
            mailHandler.setSubject("회원가입 서비스 이메일 인증 입니다.");
            mailHandler.setFrom(classyEmail);
            mailHandler.setTo(email);
            mailHandler.setText(setTemplateEngine());
            mailHandler.send();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return authNumber;
    }

    public String setTemplateEngine() {
        Map<String, String> map = new HashMap<>();
        Context context = new Context();

        map.put("authNumber", this.authNumber + "");
        map.forEach(context::setVariable);

        return templateEngine.process("authentication", context);
    }

    public void makeRandomNumber() {
        Random r = new Random();
        int checkNum = r.nextInt(888888) + 111111;
        authNumber = checkNum;
    }
}
