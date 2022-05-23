package com.cards.zokudo.controllers;

import com.cards.zokudo.dto.request.CorporateProcessorFilterDto;
import com.cards.zokudo.dto.request.FilteredProgramListDto;
import com.cards.zokudo.entities.Processor;
import com.cards.zokudo.exceptions.BizException;
import com.cards.zokudo.services.DownloadService;
import com.cards.zokudo.services.corporateprocessor.CorporateProcessorInf;
import com.cards.zokudo.services.corporateprocessor.ProcessorDTO;
import com.cards.zokudo.services.program.list.ListInf;
import com.cards.zokudo.services.program.persist.ProgramInf;
import com.cards.zokudo.services.program.persist.ProgramRequestDTO;
import com.cards.zokudo.services.program.update.UpdateInf;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("{programUrl}/api/v1/program")
public class ProgramController {

    private final ProgramInf programInf;
    private final ListInf listInf;
    private final UpdateInf updateInf;
    private final CorporateProcessorInf corporateProcessorInf;
    private final DownloadService downloadService;


    @Autowired
    public ProgramController(ProgramInf programInf, ListInf listInf, UpdateInf updateInf, CorporateProcessorInf corporateProcessorInf,
                             DownloadService downloadService) {
        this.programInf = programInf;
        this.listInf = listInf;
        this.updateInf = updateInf;
        this.corporateProcessorInf = corporateProcessorInf;
        this.downloadService = downloadService;

    }

    @ApiOperation(value = "Create a program", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/create", produces = "application/json")
    public Object createProgram(@ModelAttribute ProgramRequestDTO programDTO, @PathVariable("programUrl") String programUrl) {
        return programInf.execute(programDTO, programUrl);
    }

    @ApiOperation(value = "List of program", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/list", produces = "application/json")
    public Object programList(HttpServletRequest request) {
        return listInf.execute(request.getHeader("client_id"), request.getHeader("page"), request.getHeader("size"));
    }

    @ApiOperation(value = "Get Program list with filters", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/listProgramWithFilters", produces = "application/json")
    public Object listProgramWithFilters(HttpServletRequest request, HttpServletResponse response, @RequestBody FilteredProgramListDto filteredProgramListDto) {
        return listInf.listProgramWithFilters(request, response, filteredProgramListDto);
    }

    @ApiOperation(value = "block or unblock a program", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/blockUnblock", produces = "application/json")
    public Object blockUnblock(@RequestBody ProgramRequestDTO programDTO) {
        return updateInf.executeBlockUnblock(programDTO);
    }

    @ApiOperation(value = "Corporate processor mapping", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/addCorporateProcessor", produces = "application/json")
    public Object addCorporateProcessor(@RequestBody ProcessorDTO processorDTO) {
        return corporateProcessorInf.execute(processorDTO);
    }

    @ApiOperation(value = "List of Corporate Processors", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/listCorporateProcessor", produces = "application/json")
    public Object listCorporateProcessor() {
        return listInf.executeListProcessors();
    }

    @ApiOperation(value = "LIST CORPORATE PROCESSOR", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/listcorporate", produces = "application/json")
    public Object corparateProcessorList(HttpServletRequest request) {
        return listInf.executeListProcessors(request.getHeader("role"), request.getHeader("page"), request.getHeader("size"));
    }


    @ApiOperation(value = "LIST PROCESSOR", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/fetchProcessors", produces = "application/json")
    public List<Processor> processors(HttpServletRequest request) {
        return listInf.fetchProcessors();
    }


    @ApiOperation(value = "LIST OF CORPORATE PROCESSOR BASED ON FILTERS", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/filteredCorporateProcessorList", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object filteredCorparateProcessorList(HttpServletRequest request, HttpServletResponse response, @RequestBody CorporateProcessorFilterDto requestBody) {
        return listInf.filteredCorparateProcessorList(request, response, requestBody);
    }

    @ApiOperation(value = "download program list", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/downloadProgramList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void downloadProgramList(HttpServletRequest request, HttpServletResponse response,
                                    @PathVariable("programUrl") String programUrl,
                                    @RequestParam final Map<String, String> requestParams) throws Exception {
        try {
            downloadService.downloadProgramList(request, response, programUrl, requestParams);
        } catch (BizException e) {
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    @ApiOperation(value = "download client list", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/downloadClientList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void downloadClientList(HttpServletRequest request, HttpServletResponse response,
                                   @PathVariable("programUrl") String programUrl,
                                   @RequestParam final Map<String, String> requestParams) throws Exception {
        try {
            downloadService.downloadClientList(request, response, programUrl, requestParams);
        } catch (BizException e) {
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    @ApiOperation(value = "Create a program", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/updateProgram", produces = "application/json")
    public Object updateProgram(@ModelAttribute ProgramRequestDTO programDTO) {
        return programInf.updateProgram(programDTO);
    }

    @ApiOperation(value = "program details", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/programdetails", produces = "application/json")
    public Object programdetails(HttpServletRequest request) {
        return listInf.executeprogramlist(request.getHeader("client_id"), request.getHeader("programHashId"));
    }

    @ApiOperation(value = "Get Program by id", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/{programId}/programById")
    public ResponseEntity<?> getProgramById(@PathVariable(value = "programId") @NotEmpty(message = "programId can't be null") String programId) {
        return programInf.findProgramById(programId);
    }

    @ApiOperation(value = "Ger List of ProgramId and ProgramName", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/getListOfProgramIdAndName")
    public ResponseEntity<?> getListOfProgramIdAndName(@ModelAttribute ProgramRequestDTO programDTO) {
        return programInf.getListOfProgramIdAndName();
    }

    @ApiOperation(value = "Ger List of ProgramId and ProgramName By programIds as input", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/getListOfProgramIdAndNameByIds")
    public ResponseEntity<?> getListOfProgramIdAndNameByIds(@RequestBody ProgramRequestDTO programDTO) {
        return programInf.getListOfProgramIdAndNameByProgramIds(programDTO);
    }

    @ApiOperation(value = "Get Program Type Details", authorizations = {@Authorization("basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/{programId}/getProgramType")
    public ResponseEntity<?> getProgramType(@PathVariable("programId") Long programId) {
        return programInf.getProgramType(programId);
    }

    @ApiOperation(value = "check if client has already Program (GPR/GC) onboarded", authorizations = {@Authorization("basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/getProgramforClient")
    public ResponseEntity<?> getProgramforClient(HttpServletRequest request) {
        return programInf.getProgramforClient(request.getHeader("clientHashId"));
    }

    @ApiOperation(value = "check if client has existing programPlan", authorizations = {@Authorization("basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/getProgramPlan")
    public ResponseEntity<?> getProgramPlanforClient(HttpServletRequest request) {
        return programInf.getProgramPlanforClient(request.getHeader("clientHashId"));
    }

    @ApiOperation(value = "get list agents for all clients/SuperDistriButor/Distributor", authorizations = {@Authorization("basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/getListOfClientIds")
    public ResponseEntity<?> getListOfClients(@PathVariable("programUrl") String programUrl, HttpServletRequest request) {
        return programInf.getListOfClients(programUrl, request.getHeader("role"), request.getHeader("loginUserHashId"));
    }

    @ApiOperation(value = "get list programs by ProgramPlans", authorizations = {@Authorization("basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/getListOfProgramsByProgramPlans")
    public Object getListOfProgramsByProgramPlans(@PathVariable("programUrl") String programUrl, HttpServletRequest request) {
        return programInf.getListOfProgramsByProgramPlans(programUrl);
    }

    @ApiOperation(value = "List of program by client", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/programListByClientId", produces = "application/json")
    public Object programListByClientId(HttpServletRequest request) {
        return listInf.getProgramListByClient(request.getHeader("client_id"), request.getHeader("page"), request.getHeader("size"));
    }

    @ApiOperation(value = "get program by program name", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/getProgramByProgramName", produces = "application/json")
    public Object getProgramByProgramName(HttpServletRequest request) {
        return listInf.getProgramByProgramName(request.getHeader("programName"));
    }
}
