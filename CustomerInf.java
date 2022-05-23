package com.cards.zokudo.services;

import com.cards.zokudo.dto.request.APIRequestDto;
import com.cards.zokudo.response.ApiResponse;

import javax.servlet.http.HttpServletRequest;

public interface CustomerInf {

    ApiResponse<Object> addCustomer(APIRequestDto apiRequestDto, HttpServletRequest request, String programUrl);
}
