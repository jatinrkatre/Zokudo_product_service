package com.cards.zokudo.util;

import com.cards.zokudo.exceptions.BizException;
import com.cards.zokudo.services.impl.JWTTokenServiceImpl;
import com.google.common.collect.Maps;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
public class SecurityUtil {

    private final String springPassword;
    private final String applicationLevelUserName;
    private final JWTTokenServiceImpl jwtTokenService;

    @Autowired
    public SecurityUtil(@Value("${applicationLevel.user.password}") final String springPassword,
                        @Value("${applicationLevel.user.name}") final String applicationLevelUserName,
                        final JWTTokenServiceImpl jwtTokenService) {
        this.springPassword = springPassword;
        this.applicationLevelUserName = applicationLevelUserName;
        this.jwtTokenService = jwtTokenService;
    }


    public String getBearerAuthorizationHeader(final String token) {
        return "Bearer " + token;
    }

    /*public String getAuthorizationHeader() {
        try {
            final String userNameAndPassword = applicationLevelUserName + ":" + springPassword;
            final String encodedCredentials = Base64.getEncoder().encodeToString(userNameAndPassword.getBytes());
            return "Basic " + encodedCredentials;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "";
        }
    }*/

    public String getAuthorizationHeader() {
        try {
            log.info(" Generating JWT Token. ");
            String jwtToken  = jwtTokenService.generateJwtToken(this.applicationLevelUserName, Maps.newHashMap());
            return jwtToken;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "";
        }
    }

}
