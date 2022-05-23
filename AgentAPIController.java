package com.cards.zokudo.controllers;

import com.cards.zokudo.dto.request.AgentDTO;
import com.cards.zokudo.dto.request.DistributorDTO;
import com.cards.zokudo.response.ApiResponse;
import com.cards.zokudo.services.AgentInf;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("{programUrl}/api/v1/agent")
public class AgentAPIController {

    private final AgentInf agentInf;

    public AgentAPIController(AgentInf agentInf) {
        this.agentInf = agentInf;
    }

    @ApiOperation(value = "add Agent", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/addAgent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse onBoardAgent(@ModelAttribute AgentDTO dto, HttpServletRequest request, @PathVariable("programUrl") String programUrl) {
        return agentInf.onBoardAgent(dto, request, programUrl);
    }

    @ApiOperation(value = "Get Agent", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/listAgent", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object agentList(@RequestBody AgentDTO agentDto , HttpServletRequest request, HttpServletResponse response, @PathVariable("programUrl") String programUrl) {
        return agentInf.agentList(agentDto, request.getHeader("role"), programUrl);
    }

    @ApiOperation(value = "Create a program", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/blockUnblockAgent", produces = "application/json")
    public Object blockUnblock(@RequestBody AgentDTO agentDto) {
        return agentInf.executeBlockUnblock(agentDto);
    }

    @ApiOperation(value = "List Agent without pagination by distributorhashId ", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/{distributorHashId}/listAgent", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object agentListByRole(HttpServletRequest request, HttpServletResponse response, @PathVariable("programUrl") String programUrl,
                                  @PathVariable String distributorHashId) {
        return agentInf.agentListByDsitributorHash(request.getHeader("role"), request.getHeader("productType"), programUrl, distributorHashId, request.getHeader("category"));
    }

    @ApiOperation(value = "Get User", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/getAgentDetails", produces = "application/json")
    public ApiResponse<?> getUserByHashId(HttpServletRequest request) {
        return agentInf.getAgentDetails(request.getHeader("agentHashId"));
    }

    // Without pagination
    @ApiOperation(value = "Agent's list", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/getRetailerList")
    public Object getDistributorList(@PathVariable("programUrl") String programUrl, @RequestHeader String role, HttpServletRequest request) {
        return agentInf.getRetailerList(programUrl, role, request.getHeader("loginUserHashId"));
    }
    @ApiOperation(value = "Agent's list without hashId", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/getAllRetailer")
    public Object getAllRetailer() {
        return agentInf.allRetailer();
    }

    @ApiOperation(value = "Agent by Agent ID ", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/getAgentDetailsById", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object agentByID(HttpServletRequest request, HttpServletResponse response, @PathVariable("programUrl") String programUrl
    ) {
        return agentInf.getAgentById(request.getHeader("agentId"), programUrl);
    }

    @ApiOperation(value = "Agent and Distributor Details by Agent ID ", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/getAgentAndDistDetailsByAgentId", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getAgentAndDistributorDetailsByAgentId(HttpServletRequest request, HttpServletResponse response, @PathVariable("programUrl") String programUrl
    ) {
        return agentInf.getAgentAndDistributorDetailsByAgentId(request.getHeader("agentId"), programUrl);
    }

    @ApiOperation(value = "Agent by Agent email Id ", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/getAgentDetailsByEmailId", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object agentByEmailID(HttpServletRequest request,
                                 @PathVariable("programUrl") String programUrl) {
        return agentInf.getAgentByEmailId(request.getHeader("emailId"));
    }
}
