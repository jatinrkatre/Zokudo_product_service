package com.cards.zokudo.services;


import com.cards.zokudo.dto.request.LoginDTO;
import com.cards.zokudo.dto.request.UserDTO;
import com.cards.zokudo.dto.response.AuthenticationAndAuthorizationDTO;
import com.cards.zokudo.response.UserDetailsResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

    List<AuthenticationAndAuthorizationDTO> getUserAuthDetails(HttpServletRequest request, HttpServletResponse response);

    Object getPageDetails(HttpServletRequest request, HttpServletResponse response);

    ResponseEntity<?> getUserOtpByUsername(LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response , String programUrl);

    Object findByUsername(HttpServletRequest request, HttpServletResponse response);

    ResponseEntity<?> forgetPassword(LoginDTO loginDTO, HttpServletRequest request);

    Object getUserByHashId(String userHashId);
    
    ResponseEntity<?> createUser(UserDTO dto,String programUrl);

    List<AuthenticationAndAuthorizationDTO> getUserAuthDetailsInternal(String request, String response);
}
