package com.cards.zokudo.entities;

import com.cards.zokudo.enums.OnboardingState;
import com.cards.zokudo.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "client_details", indexes = {
        @Index(name = "entity_name", columnList = "entity_name"),
        @Index(name = "email", columnList = "email"),
        @Index(name = "mobile", columnList = "mobile"),
        @Index(name = "client_hash_id", columnList = "client_hash_id")
})
@Data
public class ClientDetails extends AbstractEntity {

    @Column(name = "entity_name", nullable = false, unique = true)
    private String entityName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "mobile", nullable = false, unique = true)
    private String mobile;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "id_type")
    private String idType;

    @Column(name = "client_logo", nullable = false)
    private String clientLogo;

    @Column(name = "poc_email")
    private String pocEmail;

    @Column(name = "poc_mobile")
    private String pocMobile;

    @Column(name = "optional_poc_email")
    private String optionalPocEmail;

    @Column(name = "optional_poc_mobile")
    private String optionalPocMobile;

    @Column(name = "name_of_director", nullable = false)
    private String nameOfDirector = "";

    @Column(name = "citizenship", nullable = false)
    private String citizenship = "";

    @Temporal(TemporalType.DATE)
    @Column(name = "dob_of_director")
    private Date dobOfDirector;

    @Column(name = "copy_of_director_front")
    private String copyOfDirectorFront;

    @Column(name = "copy_of_director_back")
    private String copyOfDirectorBack;

    @Column(name = "director_address_proof_front")
    private String directorAddressProofFront;

    @Column(name = "director_address_proof_back")
    private String directorAddressProofBack;

    @Column(name = "gst_certificate")
    private String gstCertificate;

    @Column(name = "board_resolution_form_signed")
    private String boardResolutionFormSigned;

    @Column(name = "company_pan")
    private String companyPan;

    @Column(name = "agreement_sow")
    private String agreementSOW;

    @Column(name = "program_onboarding_plan")
    private String programOnboardingPlan;

    @Column(name = "projection_commitment")
    private String projectionCommitment;

    @Column(name = "incorporation_certificate")
    private String incorporationCertificate;

    @Column(name = "penalty_clause")
    private String penaltyClause;

    @Column(name = "onboarding_state")
    @Enumerated(EnumType.STRING)
    private OnboardingState onboardingState = OnboardingState.CLEAR;


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;


    @OneToOne(fetch = FetchType.EAGER)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Users users;

    @Column(name = "client_hash_id", nullable = false, unique = true)
    private String clientHashId;

}
