package com.cards.zokudo.services;

import com.cards.zokudo.request.ChangePasswordRequest;
import com.cards.zokudo.response.ApiResponse;

import javax.servlet.http.HttpServletRequest;

public interface ChangePasswordService {
    ApiResponse execute(ChangePasswordRequest changePasswordRequest,  String programUrl,HttpServletRequest request,String loggedInUserHashId);
}
