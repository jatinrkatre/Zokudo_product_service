package com.cards.zokudo.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class CreateRoleDto {

	private String roleName;
	private List<String> privileges;
	private String createdBy;
}
