package com.cards.zokudo.dto.request;

import lombok.Data;

@Data
public class UserDTO {
	
	private String fullName;
	private String password;
	private String mobileNo;
	private String roleName;
	private String email;
	private String createdBy;
	private String clientHashId;
	private String role;
	private String loggedInUser;
	
}
