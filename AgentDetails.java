package com.cards.zokudo.entities;

import com.cards.zokudo.enums.Status;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "agent_details")
public class AgentDetails extends AbstractEntity {

    @Column(name = "company_name", nullable = false, unique = true)
    private String companyName;

    @Column(name = "full_name")
    private String fullName;

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

    @Column(name = "director_address_proof_front")
    private String directorAddressProofFront;

    @Column(name = "director_address_proof_back")
    private String directorAddressProofBack;

    @Column(name = "gst_certificate")
    private String gstCertificate;

    @Column(name = "company_pan")
    private String companyPan;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "user_hash_id")
    private String userHashId;

    @Column(name = "program_hash_id")
    private String programHashId;

    @Column(name = "distributor_hash_id")
    private String distributorHashId;


    public String getDistributorHashId() {
        return distributorHashId;
    }

    public void setDistributorHashId(String distributorHashId) {
        this.distributorHashId = distributorHashId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getNameOfDirector() {
        return nameOfDirector;
    }

    public void setNameOfDirector(String nameOfDirector) {
        this.nameOfDirector = nameOfDirector;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public Date getDobOfDirector() {
        return dobOfDirector;
    }

    public void setDobOfDirector(Date dobOfDirector) {
        this.dobOfDirector = dobOfDirector;
    }

    public String getDirectorAddressProofFront() {
        return directorAddressProofFront;
    }

    public void setDirectorAddressProofFront(String directorAddressProofFront) {
        this.directorAddressProofFront = directorAddressProofFront;
    }

    public String getDirectorAddressProofBack() {
        return directorAddressProofBack;
    }

    public void setDirectorAddressProofBack(String directorAddressProofBack) {
        this.directorAddressProofBack = directorAddressProofBack;
    }

    public String getGstCertificate() {
        return gstCertificate;
    }

    public void setGstCertificate(String gstCertificate) {
        this.gstCertificate = gstCertificate;
    }

    public String getCompanyPan() {
        return companyPan;
    }

    public void setCompanyPan(String companyPan) {
        this.companyPan = companyPan;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUserHashId() {
        return userHashId;
    }

    public void setUserHashId(String userHashId) {
        this.userHashId = userHashId;
    }

    public String getProgramHashId() {
        return programHashId;
    }

    public void setProgramHashId(String programHashId) {
        this.programHashId = programHashId;
    }
}
