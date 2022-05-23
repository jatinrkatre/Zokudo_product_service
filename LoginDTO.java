package com.cards.zokudo.dto.request;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LoginDTO {

    private String userName;
    private String password;
    private String otp;
    private String captchaResponse;
}
