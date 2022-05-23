package com.cards.zokudo.services;

import com.cards.zokudo.dto.request.APIRequestDto;
import com.cards.zokudo.response.ApiResponse;

import javax.servlet.http.HttpServletRequest;

public interface ClientAdminInf {
    ApiResponse<Object> addClientAdmin(APIRequestDto apiRequestDto, HttpServletRequest request, String programUrl);
}
