package com.cards.zokudo.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cards.zokudo.dto.request.UserDTO;
import com.cards.zokudo.services.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("{programUrl}/api/v1/user")
public class UsersApiController {

	@Autowired
	UserService service ;
	
	@ApiOperation(value = "create user", authorizations = {@Authorization(value ="basicAuth")})
	@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
	@PostMapping(value="/addUser")
	public ResponseEntity<?> createUser(@RequestBody UserDTO dto,@PathVariable("programUrl") String programUrl,HttpServletRequest request){
		
		return service.createUser(dto, programUrl); 
	}
}
