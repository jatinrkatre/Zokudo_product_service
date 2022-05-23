package com.cards.zokudo.services;

import com.cards.zokudo.dto.request.IPWhiteListingDTO;
import com.cards.zokudo.dto.request.WebhookUrlDto;
import com.cards.zokudo.entities.Program;
import com.cards.zokudo.response.ApiResponse;

import javax.servlet.http.HttpServletRequest;

public interface ProductService {
    Program getProductDetailsByHostUrl(String program_url);

    Object updateProgramBalance(HttpServletRequest request , String programUrl);

    Object getProductDetailsById(String programUrl , String  program_id);

    ApiResponse getProgramBalance(String programId, String loggedInUserRole, String loggedInUserHashId);
    Object getProgramList();

    Object getProductById(String programHashId);

    ApiResponse<Object> ipWhiteList(IPWhiteListingDTO ipWhiteListingDTO, String programUrl);

    ApiResponse<Object> getIPList(String page , String size);

    ApiResponse<Object> getIpDetails(String ipAddress);

    ApiResponse<Object> ipBlackList(IPWhiteListingDTO ipWhiteListingDTO);

    ApiResponse<Object> getBinNumber(String reloadableType);

    ApiResponse<Object> getSubBinNumber();

    ApiResponse<Object> getChannelDetails(String loggedInUserHashId, String programUrl);

    ApiResponse<Object> getSubBinByProgram(String programId);

    ApiResponse getProgramBalancePartnerApi(String programUrl,String loggedInUserRole,String loginUserHashId);

    ApiResponse<Object> webhookUrlConfigure(WebhookUrlDto webhookUrlDto);

    ApiResponse<Object> getWebhookUrls();
}
