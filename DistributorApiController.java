package com.cards.zokudo.controllers;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cards.zokudo.exceptions.BizException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.cards.zokudo.dto.request.DistributorDTO;
import com.cards.zokudo.entities.DistributorEntity;
import com.cards.zokudo.response.ApiResponse;
import com.cards.zokudo.services.DistributorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

import java.util.Map;

@RestController
@RequestMapping("{programUrl}/api/v1/distributor")
public class DistributorApiController {

	private final DistributorService distributorService;
	
	public DistributorApiController(DistributorService distributorService) {
		this.distributorService = distributorService;
	}
	
	
	@ApiOperation(value = "add Distributor", authorizations = {@Authorization(value = "basicAuth")})
	@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse<Object> onBoardDistributor(@ModelAttribute DistributorDTO dto, HttpServletRequest request, @PathVariable("programUrl") String programUrl) {
		return distributorService.onBoardDistributor(dto, request, programUrl);
	}

	/**	
	 * 
	 * Distributor's list with pagination
	 * @param request
	 * @param response
	 * @param programUrl
	 * @return
	 */
	@ApiOperation(value = "Get Distributor", authorizations = {@Authorization(value = "basicAuth")})
	@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
	@PostMapping (value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object distributorList(@RequestBody DistributorDTO dto ,HttpServletRequest request, HttpServletResponse response, @PathVariable("programUrl") String programUrl) {
		return distributorService.distributorList(dto,request.getHeader("role"),programUrl);
	}

	@ApiOperation(value = "Get Distributor By Id", authorizations = {@Authorization(value = "basicAuth")})
	@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
	@GetMapping(value = "/details/id", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object distributorById(HttpServletRequest request) {
		return distributorService.getDistributorById(request.getHeader("id"));
	}
	
	@ApiOperation(value = "Update Distributor", authorizations = {@Authorization(value = "basicAuth")})
	@CrossOrigin(allowCredentials = "true",allowedHeaders = "*",origins = {"*"})
	@PostMapping(value="/update")
	public ApiResponse<Object> updateDistributor(@ModelAttribute DistributorDTO dto,@PathVariable("programUrl") String programUrl) {
		
		return distributorService.updateDistributor(dto,programUrl);
	}
	/**
	 * Distributor's list for particular program
	 * @param programUrl
	 * @return
	 */
	@ApiOperation(value = "Distributors by program", authorizations = {@Authorization(value = "basicAuth")})
	@CrossOrigin(allowCredentials = "true",allowedHeaders = "*",origins = {"*"})
	@GetMapping(value="/getDistByProgram")
	public Object getDistributorByProgramHash(@PathVariable("programUrl") String programUrl) {
		return distributorService.getDistributorByProgramHash(programUrl);
	}
	/**
	 * 
	 * Distributor's list without pagination based on role.
	 * list All for ROLE_ADMIN
	 * list by program for ROLE_CLIENT
	 * @param programUrl
	 * @return
	 */
	@ApiOperation(value = "Distributors's list", authorizations = {@Authorization(value = "basicAuth")})
	@CrossOrigin(allowCredentials = "true",allowedHeaders = "*",origins = {"*"})
	@GetMapping(value="/getDistributorList")
	public Object getDistributorList(@PathVariable("programUrl") String programUrl,@RequestHeader String role, HttpServletRequest request) {
		return distributorService.getDistributorList(programUrl,role,request.getHeader("loginUserHashId"));
	}
	
	/**	
	 * 
	 */
	@ApiOperation(value = "Get distributor by userhashId ", authorizations = {@Authorization(value = "basicAuth")})
	@CrossOrigin(allowCredentials = "true",allowedHeaders = "*",origins = {"*"})
	@GetMapping(value="/{userHashId}/getDistByUserHashId")
	public DistributorEntity getDistributorByUserHashId(@PathVariable("programUrl") String programUrl,@PathVariable String userHashId) {
		return distributorService.getDistribtorByUserHashId(userHashId);
	}

	@ApiOperation(value = "Get Distributor", authorizations = {@Authorization(value = "basicAuth")})
	@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
	@GetMapping (value = "/downloadlist", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void distributorDownloadList(@RequestParam final Map<String, String> requestParams,
										HttpServletRequest request, HttpServletResponse response, @PathVariable("programUrl") String programUrl) throws Exception {
		try {
			 distributorService.distributorListForDownload(requestParams,request,response,programUrl);
		} catch (BizException e) {
			e.printStackTrace();
			 response.sendRedirect(request.getHeader("Referer"));
		}
	}
}
