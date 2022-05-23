package com.cards.zokudo.dto.request;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Data
public class ClientOnboardDTO {
    private long clientId;
    private String entityName;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String idNumber;
    private String idType;
    private String address;
    private MultipartFile clientLogo;
    private String usersId;
    private String actionType;
    private String userHashId;
    private String pocEmail;
    private String pocMobile;
    private String optionalPocEmail; // optional
    private String optionalPocMobile; // optional
    private String nameOfDirector;
    private String citizenship;
    private String dobOfDirector;
    private MultipartFile copyOfDirectorFront;
    private MultipartFile copyOfDirectorBack;
    private MultipartFile directorAddressProofFront;
    private MultipartFile directorAddressProofBack;
    private MultipartFile gstCertificate;
    private MultipartFile boardResolutionFormSigned; //optional
    private MultipartFile companyPan;
    private MultipartFile agreementSOW; //optional
    private MultipartFile programOnboardingPlan; //optional
    //private MultipartFile projectionCommitment; //optional
    //private MultipartFile incorporationCertificate;
    private MultipartFile penaltyClause; //optional
    private String password;

}
