package com.cards.zokudo.controllers;


import com.cards.zokudo.dto.request.IPWhiteListingDTO;
import com.cards.zokudo.dto.request.LoginDTO;
import com.cards.zokudo.dto.request.WebhookUrlDto;
import com.cards.zokudo.request.ChangePasswordRequest;
import com.cards.zokudo.request.UpdatePasswordRequest;
import com.cards.zokudo.response.ApiResponse;
import com.cards.zokudo.response.UserDetailsResponse;
import com.cards.zokudo.services.ChangePasswordService;
import com.cards.zokudo.services.ProductService;
import com.cards.zokudo.services.UpdatePasswordService;
import com.cards.zokudo.services.UserService;
import com.cards.zokudo.util.AESEncryption;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("{programUrl}/api/v1/product")
public class ProductAPIController {
    private final UserService userService;
    private final ProductService productService;
    private final ChangePasswordService changePasswordService;
    private final UpdatePasswordService updatePasswordService;

    @Autowired
    public ProductAPIController(UserService userService, ProductService productService, ChangePasswordService changePasswordService, UpdatePasswordService updatePasswordService) {
        this.userService = userService;
        this.productService = productService;
        this.changePasswordService = changePasswordService;
        this.updatePasswordService = updatePasswordService;
    }


    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/userAuthDetails")
    public String getString(HttpServletRequest request, HttpServletResponse response) {
        return AESEncryption.encrypt(userService.getUserAuthDetails(request, response).toString(), AESEncryption.secretKey);
    }


    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/findByUsername")
    public Object findByUserName(HttpServletRequest request, HttpServletResponse response) {
        return userService.findByUsername(request, response);
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/programDetails")
    public Object getProgramDetails(@PathVariable("programUrl") String programUrl) {
        return productService.getProductDetailsByHostUrl(programUrl);
    }


    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/{programId}/programById")
    public Object getProductDetailsById(@PathVariable("programUrl") String programUrl, @PathVariable("programId") String programId) {
        return productService.getProductDetailsById(programUrl, programId);
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/programByHashId")
    public Object getProgramByHashId(@PathVariable("programUrl") String programHashId) {
        return productService.getProductById(programHashId);
    }


    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PutMapping(value = "/updateProgramBalance")
    public Object updateProgramBalance(HttpServletRequest request, @PathVariable("programUrl") String programUrl) {
        return productService.updateProgramBalance(request, programUrl);
    }


    //    @ApiOperation(value = "Get page details", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/getPageDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPageDetails(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<Object>(userService.getPageDetails(request, response), HttpStatus.OK);
    }

    @ApiOperation(value = "Validate and Get User Login OTP", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/sendOTP", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> verifyUserLoginOTPByUsername(@RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response,
                                                          @PathVariable("programUrl") String programUrl) {
        return userService.getUserOtpByUsername(loginDTO, request, response, programUrl);
    }

    @ApiOperation(value = "Change Password", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/changePassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse changePasswordService(@RequestBody ChangePasswordRequest changePasswordRequest, @PathVariable("programUrl") String programUrl, HttpServletRequest request) {

        return changePasswordService.execute(changePasswordRequest, programUrl, request, request.getHeader("loggedInUserHashId"));
    }

    @ApiOperation(value = "Program Balance", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/getProgramBalance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse getProgramBalanceSum(HttpServletRequest request) {

        return productService.getProgramBalance(request.getHeader("programId"), request.getHeader("loggedInUserRole"), request.getHeader("loginUserHashId"));
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/forgetPassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> forgetPassword(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        return userService.forgetPassword(loginDTO, request);
    }

    @ApiOperation(value = "Get Program List", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/getProgramList", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getProgramLsit() {
        return productService.getProgramList();
    }

    @ApiOperation(value = "Get Program List", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/getWebhookUrls", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getWebhookUrls() {
        return productService.getWebhookUrls();
    }


    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/updatePassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Object> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest, HttpServletRequest request, @PathVariable("programUrl") String programUrl) {
        return updatePasswordService.execute(updatePasswordRequest, programUrl);
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/ipwhitelist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Object> ipWhiteList(@RequestBody IPWhiteListingDTO ipWhiteListingDTO, HttpServletRequest request, @PathVariable("programUrl") String programUrl) {
        return productService.ipWhiteList(ipWhiteListingDTO, programUrl);
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/getIPList", produces = MediaType.APPLICATION_JSON_VALUE)

    public ApiResponse<Object> getIPList(HttpServletRequest request) {
        return productService.getIPList(request.getHeader("page"), request.getHeader("size"));
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/ipBlackList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Object> ipBlackList(@RequestBody IPWhiteListingDTO ipWhiteListingDTO) {
        return productService.ipBlackList(ipWhiteListingDTO);
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @GetMapping(value = "/getIpDetails/{ipAddress}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Object> getIpDetails(@PathVariable("ipAddress") String ipAddress) {
        return productService.getIpDetails(ipAddress);
    }

    @ApiOperation(value = "GET BIN NUMBER FOR RELOADABLE TYPE - GPR, GC, GC1", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/getBinNumber", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Object> getBinNumber(HttpServletRequest request) {
        return productService.getBinNumber(request.getHeader("reloadableCardType"));
    }

    @ApiOperation(value = "GET SUB BIN NUMBER FOR CARD TYPE - PHY, VIR, BOTH", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/getSubBinNumber", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Object> getSubBinNumber(HttpServletRequest request) {
        return productService.getSubBinNumber();
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/getChannelDetails", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getChannelDetails(@PathVariable(value = "programUrl") final String programUrl, HttpServletRequest request) {
        return new ResponseEntity<>(productService.getChannelDetails(request.getHeader("loggedInUserHashId"), programUrl), HttpStatus.OK);
    }

    @ApiOperation(value = "GET SUB BIN NUMBER By program ID", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/getBinByProgram", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Object> getBinByProgramId(HttpServletRequest request) {
        return productService.getSubBinByProgram(request.getHeader("programId"));
    }

    @ApiOperation(value = "Program Balance for partner API", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/getProgramBalanceForPartnerApi", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse getProgramBalanceSumForPartnerApi(@PathVariable("programUrl")String programUrl,HttpServletRequest request) {

        return productService.getProgramBalancePartnerApi(programUrl, request.getHeader("loggedInUserRole"), request.getHeader("loginUserHashId"));
    }
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/configureWebhookUrl", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Object> webhookUrlConfigure (@RequestBody WebhookUrlDto webhookUrlDto){
            return productService.webhookUrlConfigure(webhookUrlDto);
    }
}
