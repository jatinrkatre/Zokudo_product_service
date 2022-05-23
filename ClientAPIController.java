package com.cards.zokudo.controllers;


import com.cards.zokudo.dto.request.APIRequestDto;
import com.cards.zokudo.dto.request.ClientOnboardDTO;
import com.cards.zokudo.dto.request.OnboardingOtpRequestDTO;
import com.cards.zokudo.response.ApiResponse;
import com.cards.zokudo.services.ClientAdminInf;
import com.cards.zokudo.services.ClientService;
import com.cards.zokudo.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("{programUrl}/api/v1/client")
public class ClientAPIController {


    private final ClientService clientService;
    private final UserService userService;
    private final ClientAdminInf clientAdminInf;


    @Autowired
    public ClientAPIController(final ClientService clientService, final ClientAdminInf clientAdminInf, UserService userService) {
        this.clientService = clientService;
        this.clientAdminInf = clientAdminInf;
        this.userService = userService;
    }


    @ApiOperation(value = "add Client", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/onboardClient", produces = MediaType.APPLICATION_JSON_VALUE)
//, consumes = {"multipart/form-data"})
    public ApiResponse<Object> clientOnBoard(@ModelAttribute ClientOnboardDTO clientOnboardDTO, HttpServletRequest request, @PathVariable("programUrl") String programUrl) {
        return clientService.addClient(clientOnboardDTO, request, programUrl);
    }

    @ApiOperation(value = "Get Client", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/getclient", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getClient(HttpServletRequest request, HttpServletResponse response) {
        return clientService.getclient(request.getHeader("role"), request.getHeader("page"), request.getHeader("size"));
    }

    @ApiOperation(value = "block Unblock Client", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/blockUnblockClient", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Object> blockUnblockClient(@RequestBody ClientOnboardDTO clientOnboardDTO, HttpServletRequest request, @PathVariable("programUrl") String programUrl) {
        return clientService.blockUnblockClient(clientOnboardDTO, request.getHeader("role"), programUrl);
    }

    @ApiOperation(value = "Get Users", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/getUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getUsers(HttpServletRequest request, HttpServletResponse response) {
        return clientService.getusers(request);
    }

    @ApiOperation(value = "Get filtered Users", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/getFilteredUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getFilteredUsers(HttpServletRequest request, HttpServletResponse response, @RequestBody APIRequestDto apiRequestDto) {
        return clientService.getFilteredUsers(request, response, apiRequestDto);
    }

    @ApiOperation(value = "block Unblock Users", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/blockUnblockUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Object> blockUnblockUsers(@RequestBody ClientOnboardDTO clientOnboardDTO, HttpServletRequest request, @PathVariable("programUrl") String programUrl) {
        return clientService.blockUnblockUsers(clientOnboardDTO, request.getHeader("role"), programUrl);
    }

    @ApiOperation(value = "Verify Onboarding OTP", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/verifyOnboardingOTP", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Object> verifyOnboardingOTP(@RequestBody OnboardingOtpRequestDTO onboardingOtpRequestDTO, HttpServletRequest request, @PathVariable("programUrl") String programUrl) {
        return clientService.verifyOnboardingOTP(onboardingOtpRequestDTO, request, programUrl);
    }

    @ApiOperation(value = "Onboarding Client Admin", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/addClientAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Object> addClientAdmin(@RequestBody APIRequestDto apiRequestDto, HttpServletRequest request, @PathVariable("programUrl") String programUrl) {
        return clientAdminInf.addClientAdmin(apiRequestDto, request, programUrl);
    }

    @ApiOperation(value = "add Client", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/updateClient", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {"multipart/form-data"})
    public ApiResponse<Object> updateClientDetails(@ModelAttribute ClientOnboardDTO clientOnboardDTO, HttpServletRequest request, @PathVariable("programUrl") String programUrl) {
        return clientService.updateClientDetails(clientOnboardDTO, request, programUrl);
    }
    
    /*@ApiOperation(value = "client details", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/getClientById", produces = "application/json")
    public Object getclientById(HttpServletRequest request) {
        return clientService.getclientById(request.getHeader("client_id"),request.getHeader ("id"));
    }*/

    @ApiOperation(value = "client details", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/getClientById", produces = "application/json")
    public Object getclientById(HttpServletRequest request) {
        return clientService.getclientById(request.getHeader("id"));
    }

    @ApiOperation(value = "Get User", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/getUserByHashId", produces = "application/json")
    public Object getUserByHashId(HttpServletRequest request) {
        return userService.getUserByHashId(request.getHeader("user_hash_id"));
    }

    @ApiOperation(value = "Get Client", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/getClientList", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getClientList(HttpServletRequest request, HttpServletResponse response) {
        return clientService.getClientList(request.getHeader("role"));
    }

    @ApiOperation(value = "Fetch client info", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/getClientOnfilter", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getClientOnfilter(HttpServletRequest request, HttpServletResponse
            response, @PathVariable("programUrl") String programUrl, @RequestBody APIRequestDto apiRequestDto) {
        return clientService.getClientOnfilter(apiRequestDto);
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/{clientHashId}/fetchClientId", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getClientByClientCode(@PathVariable(value = "clientHashId") final String clientHashId) {
        return new ResponseEntity<>(clientService.fetchClientByClientCode(clientHashId, true), HttpStatus.OK);
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/customerOnboardingData", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchDataForCustomerOnboarding(@PathVariable(value = "programUrl") final String programUrl, HttpServletRequest request) {
        return new ResponseEntity<>(clientService.fetchDataForCustomerOnboarding(request.getHeader("loggedInUserHashId"), programUrl), HttpStatus.OK);
    }

    @ApiOperation(value = "client details by email id", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/getClientByEmailId", produces = "application/json")
    public Object getClientByEmailId(HttpServletRequest request) {
        return clientService.getClientByEmailId(request.getHeader("emailId"));
    }
    
    @ApiOperation(value = "Password Encryptor", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/encryptPassword", produces = "application/json")
    public Object encryptPassword(HttpServletRequest request) {
        return clientService.encryptPassword(request.getHeader("password"));
    }

    @ApiOperation(value = "Password Encryptor", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/verifyPassword", produces = "application/json")
    public Object verifyPassword(HttpServletRequest request) {
        return clientService.verifyPassword(request.getHeader("encPassword"), request.getHeader("rawPassword"));
    }

    @ApiOperation(value = "Get Client List With Program Plan", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/clientListByProgramPlan", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object clientListByProgramPlan(HttpServletRequest request, HttpServletResponse response) {
        return clientService.clientListByProgramPlan(request.getHeader("role"));

    }

}
