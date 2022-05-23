package com.cards.zokudo.entities;

import com.cards.zokudo.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;


@Entity
@Table(name = "commercials", indexes = {
        @Index(name = "client_id", columnList = "client_id"),
        @Index(name = "hash_id", columnList = "hash_id")
})
public class Commercials extends AbstractEntity {

    @Column(name = "one_time_fee", nullable = false)
    private float oneTimeFee;

    @Column(name = "is_fixed", nullable = false)
    private boolean isFixed;

    @Column(name = "revenue_sharing", nullable = false)
    private String revenueSharing;

    @Column(name = "monthly_maintenance", nullable = false)
    private float monthlyMaintenance;

    @Column(name = "virtual_card_cost", nullable = false)
    private double virtualCardCost;

    @Column(name = "physical_card_cost", nullable = false)
    private double physicalCardCost;

    @Column(name = "remarks", nullable = false)
    private String remarks;

    @Column(name = "hash_id")
    private String hashId;

    @Column(name = "otp")
    private String otp;

    @Column(name = "commercial_doc_url")
    private String commercialDocUrl;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Program program;
    
    @Column(name = "client_id")
    private Long clientId;

    
    public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public float getOneTimeFee() {
        return oneTimeFee;
    }

    public void setOneTimeFee(float oneTimeFee) {
        this.oneTimeFee = oneTimeFee;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }

    public String getRevenueSharing() {
        return revenueSharing;
    }

    public void setRevenueSharing(String revenueSharing) {
        this.revenueSharing = revenueSharing;
    }

    public float getMonthlyMaintenance() {
        return monthlyMaintenance;
    }

    public void setMonthlyMaintenance(float monthlyMaintenance) {
        this.monthlyMaintenance = monthlyMaintenance;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getCommercialDocUrl() {
        return commercialDocUrl;
    }

    public void setCommercialDocUrl(String commercialDocUrl) {
        this.commercialDocUrl = commercialDocUrl;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public double getVirtualCardCost() {
        return virtualCardCost;
    }

    public void setVirtualCardCost(double virtualCardCost) {
        this.virtualCardCost = virtualCardCost;
    }

    public double getPhysicalCardCost() {
        return physicalCardCost;
    }

    public void setPhysicalCardCost(double physicalCardCost) {
        this.physicalCardCost = physicalCardCost;
    }
}
