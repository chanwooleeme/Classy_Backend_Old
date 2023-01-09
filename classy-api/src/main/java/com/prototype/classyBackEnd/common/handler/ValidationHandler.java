package com.prototype.classyBackEnd.common.handler;

import com.prototype.classyBackEnd.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.xml.bind.ValidationException;

@Component
@RequiredArgsConstructor
public class ValidationHandler {

    private final MemberService memberService;
    private final BCryptPasswordEncoder passwordEncoder;

    public ValidationHandler HasValidationError(Errors errors) throws Exception{
        if(errors.hasErrors()){
            for(FieldError value:errors.getFieldErrors()){
                throw new ValidationException(value.getDefaultMessage());
            }
        }
        return this;
    }

}

