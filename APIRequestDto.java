package com.cards.zokudo.dto.request;

import com.cards.zokudo.entities.Program;
import lombok.Data;

@Data
public class APIRequestDto {

    private String first_name;
    private String last_name;
    private String fullName;
    private String mobile;
    private String email;
    private String password;
    private String programId;
    private String hostUrl;
    private String dob;
    private String address_line_one;
    private Program program;

    // user filter list

    private String dateRange;
    private String userHashId;
    private String page;
    private String size;
    private String entityName;
}
