package com.cards.zokudo.controllers;

import com.cards.zokudo.dto.request.CreateCommercialDTO;
import com.cards.zokudo.dto.request.FilterCommercialListDto;
import com.cards.zokudo.response.ApiResponse;
import com.cards.zokudo.services.CommercialService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("{programUrl}/api/v1/commercial")
public class CommercialsController {

    @Autowired
    CommercialService commercialService;

    @ApiOperation(value = "add Commercial", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/createCommercial", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {"multipart/form-data"})
    public ApiResponse<Object> createCommercial(@ModelAttribute CreateCommercialDTO createCommercialDTO, HttpServletRequest request, @PathVariable("programUrl") String programUrl) {
        return commercialService.createCommercial(createCommercialDTO, request, programUrl);
    }

    @ApiOperation (value = "get commercial list",authorizations = {@Authorization (value = "basciAuth")})
    @CrossOrigin(allowedHeaders = "*",allowCredentials = "true",methods = RequestMethod.POST,origins = {"*"})
    @PostMapping( value = "/getCommercialList",produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getCommercialList(HttpServletRequest request,@PathVariable("programUrl") String programUrl,@RequestBody FilterCommercialListDto dto) {
        return commercialService.getCommercialList(programUrl , request.getHeader("role"),request.getHeader ("page"),request.getHeader ("size"),dto);
    }

    @ApiOperation (value = "get commercial list",authorizations = {@Authorization (value = "basciAuth")})
    @CrossOrigin(allowedHeaders = "*",allowCredentials = "true",methods = RequestMethod.GET,origins = {"*"})
    @GetMapping( value = "/getCommercialByProgramId",produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse getComercialByProgramId(HttpServletRequest request,@PathVariable("programUrl") String programUrl) {
        return commercialService.getCommercialByProgramId(programUrl , request.getHeader("program_id"));
    }


    @ApiOperation(value = "add Commercial", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/editCommercial", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {"multipart/form-data"})
    public ApiResponse<Object> editCommercial(@ModelAttribute CreateCommercialDTO createCommercialDTO, HttpServletRequest request, @PathVariable("programUrl") String programUrl) {
        return commercialService.updateCommercial(createCommercialDTO, request, programUrl);
    }

    @ApiOperation (value = "get commercial By HashId",authorizations = {@Authorization (value = "basciAuth")})
    @CrossOrigin(allowedHeaders = "*",allowCredentials = "true",methods = RequestMethod.GET,origins = {"*"})
    @GetMapping( value = "/getCommercialByHashId",produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getCommercialByHashId(HttpServletRequest request,@PathVariable("programUrl") String programUrl) {
        return commercialService.getCommercialByHashId(programUrl , request.getHeader("role"),request.getHeader ("hashId"));
    }
}
