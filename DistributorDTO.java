package com.cards.zokudo.dto.request;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Component
@Data
public class DistributorDTO {

	private long id;
	private String companyName;
	private String fullName;
    private String mobile;
    private String email;
    private String password;
    private MultipartFile distributorLogo;
    private String nameOfDirector;
    private String citizenship;
    private String address;
    private String dobOfDirector;
    private MultipartFile gstCertificate;
    private MultipartFile companyPan;
    private MultipartFile copyOfDirectorFront;
    private MultipartFile copyOfDirectorBack;
    private MultipartFile projectionCommitment; 
    private MultipartFile incorporationCertificate;
    private String page;
    private String size;
    private String dateRange;

}
