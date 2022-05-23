package com.cards.zokudo.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cards.zokudo.dto.request.CreateRoleDto;
import com.cards.zokudo.services.RoleService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("{programUrl}/api/v1/role")
public class RolesApiController {

		@Autowired
		RoleService roleService;
		
		
		/**	
		 * author: vsahoo@msewa.com
		 * @param 
		 * @param programUrl
		 * @return : List of privileges
		 * 
		 */
		
		@ApiOperation(value = "Get Privileges List", authorizations = {@Authorization(value = "basicAuth")})
	    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
	    @GetMapping(value = "/privileges")
	    public ResponseEntity<?> getPrivileges(@PathVariable("programUrl") String programUrl,@RequestHeader(value = "role") String role) {
	      return roleService.getPrivileges(role);
	    }
		
		/**
		 * author : vsahoo@msewa.com
		 * @param programUrl
		 * @return: validate role name if it exists or not
		 */
		
		@ApiOperation(value = "validate role name", authorizations = {@Authorization(value = "basicAuth")})
	    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
	    @GetMapping(value = "/{roleName}/validateRole")
	    public ResponseEntity<?> validateRoleName(@PathVariable("programUrl") String programUrl,@PathVariable String roleName) {
	      return roleService.validateRoleName(roleName);
	    }
		
		/**
		 * 
		 * author : vsahoo@msewa.com
		 * @param dto : privileges, role name, created by
		 * @param programUrl
		 * @return success/ error msg.
		 */
		@ApiOperation(value = "add role with selected privileges", authorizations = {@Authorization(value ="basicAuth")})
		@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
		@PostMapping(value="/addRole")
		public ResponseEntity<?> addRole(@RequestBody CreateRoleDto dto,@PathVariable("programUrl") String programUrl,HttpServletRequest request,
				@RequestHeader("role") String role){
			
			return roleService.addRole(dto,role);
		}
		
		/**	
		 * author: vsahoo@msewa.com
		 * @param 
		 * @param programUrl
		 * @return : List of "roleid : rolename"
		 * 
		 */
		@ApiOperation(value = "Get Role List", authorizations = {@Authorization(value = "basicAuth")})
	    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
	    @GetMapping(value = "/allRoles")
	    public ResponseEntity<?> getRoleList(@PathVariable("programUrl") String programUrl,@RequestHeader("role") String role) {
	      return roleService.getRoles(role);
	    }
		
		
		
		
}
