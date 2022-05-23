package com.cards.zokudo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cards.zokudo.enums.OnboardingState;
import com.cards.zokudo.enums.Status;
import lombok.Data;

@Data
@Entity
@Table(name = "distributor")
public class DistributorEntity extends AbstractEntity {

    @Column(name = "company_name", nullable = false, unique = true)
    private String companyName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "mobile", nullable = false, unique = true)
    private String mobile;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "id_type")
    private String idType;

    @Column(name = "name_of_director", nullable = false)
    private String nameOfDirector = "";

    @Column(name = "citizenship", nullable = false)
    private String citizenship = "";

    @Temporal(TemporalType.DATE)
    @Column(name = "dob_of_director")
    private Date dobOfDirector;

    @Column(name = "gst_certificate")
    private String gstCertificate;

    @Column(name = "company_pan")
    private String companyPan;

    @Column(name = "projection_commitment")
    private String projectionCommitment;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "copy_of_director_front")
    private String copyOfDirectorFront;

    @Column(name = "copy_of_director_back")
    private String copyOfDirectorBack;

    @Column(name = "user_hash_id")
    private String userHashId;

    @Column(name = "program_hash_id")
    private String programHashId;

    @Column(name = "dist_hash_id", nullable = false, unique = true)
    private String distributorHashId;
    
    @Override
    public String toString() {
        return "DistributorEntity{" +
                "companyName='" + companyName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", idType='" + idType + '\'' +
                ", nameOfDirector='" + nameOfDirector + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", dobOfDirector=" + dobOfDirector +
                ", gstCertificate='" + gstCertificate + '\'' +
                ", companyPan='" + companyPan + '\'' +
                ", projectionCommitment='" + projectionCommitment + '\'' +
                ", status=" + status +
                ", copyOfDirectorFront='" + copyOfDirectorFront + '\'' +
                ", copyOfDirectorBack='" + copyOfDirectorBack + '\'' +
                ", userHashId='" + userHashId + '\'' +
                ", programHashId='" + programHashId + '\'' +
                ", distributorHashId='" + distributorHashId + '\'' +
                '}';
    }
}
