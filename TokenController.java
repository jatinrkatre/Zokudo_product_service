package com.cards.zokudo.controllers;

import com.cards.zokudo.response.TokenResponseDTO;
import com.cards.zokudo.services.TokenService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/token")
@RestController
public class TokenController {

    private final TokenService tokenService;

    @Autowired
    public TokenController(final TokenService tokenService){
        this.tokenService = tokenService;
    }
    @ApiOperation(value = "CREATE TOKEN BASED ON USERNAME AND PASSWORD", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @GetMapping(value = "/createToken", produces = MediaType.APPLICATION_JSON_VALUE)
    public TokenResponseDTO createToken(HttpServletRequest request, HttpServletResponse response) {
        return tokenService.createToken(request);
    }
}
