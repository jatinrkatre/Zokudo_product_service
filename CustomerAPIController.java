package com.cards.zokudo.controllers;

import com.cards.zokudo.dto.request.APIRequestDto;
import com.cards.zokudo.response.ApiResponse;
import com.cards.zokudo.services.CustomerInf;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("{programUrl}/api/v1/customer")
public class CustomerAPIController {

    private final CustomerInf customerInf;

    @Autowired
    public CustomerAPIController(final CustomerInf customerInf) {
        this.customerInf = customerInf;
    }

    @ApiOperation(value = "Add Customer", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/addCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Object> addClientAdmin(@RequestBody APIRequestDto apiRequestDto, HttpServletRequest request, @PathVariable("programUrl") String programUrl) {
      return customerInf.addCustomer(apiRequestDto, request, programUrl);
    }
}
