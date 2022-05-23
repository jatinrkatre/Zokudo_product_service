package com.cards.zokudo.services;

import com.cards.zokudo.dto.request.CreateCommercialDTO;
import com.cards.zokudo.dto.request.FilterCommercialListDto;
import com.cards.zokudo.response.ApiResponse;

import javax.servlet.http.HttpServletRequest;

public interface CommercialService {
    ApiResponse<Object> createCommercial(CreateCommercialDTO createCommercialDTO, HttpServletRequest request, String programUrl);

    Object getCommercialList(String programUrl, String role, String page, String size,FilterCommercialListDto dto);

    ApiResponse<Object> updateCommercial(CreateCommercialDTO createCommercialDTO, HttpServletRequest request, String programUrl);

    Object getCommercialByHashId(String programUrl, String role, String hashId);

    ApiResponse getCommercialByProgramId(String programUrl, String program_id);
}
