package com.cards.zokudo.services;

import org.springframework.http.ResponseEntity;

import com.cards.zokudo.dto.request.CreateRoleDto;

public interface RoleService {

	ResponseEntity<?> getPrivileges(String role);
	ResponseEntity<?> validateRoleName(String roleName);
	ResponseEntity<?> addRole(CreateRoleDto dto,String role);
	ResponseEntity<?> getRoles(String role);
}
