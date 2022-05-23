package com.cards.zokudo.services;

import com.cards.zokudo.dto.request.APIRequestDto;
import com.cards.zokudo.dto.request.ClientOnboardDTO;
import com.cards.zokudo.dto.request.OnboardingOtpRequestDTO;
import com.cards.zokudo.response.ApiResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ClientService {

    ApiResponse<Object> addClient(ClientOnboardDTO clientOnboardDTO, HttpServletRequest request,String programUrl);

    Object getclient(String role,String page, String size);

    ApiResponse<Object> blockUnblockClient(ClientOnboardDTO clientOnboardDTO, String role, String programUrl);

    Object getusers(HttpServletRequest request);

    ApiResponse<Object> blockUnblockUsers(ClientOnboardDTO clientOnboardDTO, String role, String programUrl);

    ApiResponse<Object> verifyOnboardingOTP(OnboardingOtpRequestDTO onboardingOtpRequestDTO, HttpServletRequest request, String programUrl);

    ApiResponse<Object> updateClientDetails(ClientOnboardDTO clientOnboardDTO, HttpServletRequest request, String programUrl);

    //Object getclientById(String role, String id);

    Object getclientById(String id);

    Object getFilteredUsers(HttpServletRequest request, HttpServletResponse response, APIRequestDto apiRequestDto);

    Object getClientList(String role);

    Object getClientOnfilter(APIRequestDto apiRequestDto);

    Object fetchClientByClientCode(String clientHashId, boolean b);

    Object fetchDataForCustomerOnboarding(String loggedInUserHashId, String programUrl);

    Object getClientByEmailId(String emailId);

    Object encryptPassword(String password);

    Object verifyPassword(String encPassword, String rawPassword);

    Object clientListByProgramPlan(String role);

}
