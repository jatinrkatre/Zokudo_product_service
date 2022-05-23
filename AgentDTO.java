package com.cards.zokudo.dto.request;

import com.cards.zokudo.entities.Program;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AgentDTO {

    private String companyName;
    private String fullName;
    private String mobile;
    private String email;
    private String password;
    private String nameOfDirector;
    private String citizenship;
    private String address;
    private String dobOfDirector;
    private MultipartFile directorAddressProofFront;
    private MultipartFile directorAddressProofBack;
    private MultipartFile gstCertificate;
    private MultipartFile companyPan;
    private Program program;
    private String userHashId;
    private String actionType;
    private String loggedInUserHashId;
    private String dateRage;
    private String page;
    private String size;

}
