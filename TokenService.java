package com.cards.zokudo.services;

import com.cards.zokudo.response.TokenResponseDTO;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {

    public TokenResponseDTO createToken(HttpServletRequest request);
}
