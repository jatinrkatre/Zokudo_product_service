package com.cards.zokudo.entities;

import com.cards.zokudo.enums.BalanceTypeAtProcessor;
import com.cards.zokudo.enums.ProgramPlans;
import com.cards.zokudo.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.glassfish.jersey.internal.guava.Sets;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "program", indexes = {
        @Index(name = "client_id", columnList = "client_id"),
        @Index(name = "program_hash_id", columnList = "program_hash_id"),
        @Index(name = "host_url", columnList = "host_url"),
        @Index(name = "business_id", columnList = "business_id")
})
public class Program extends AbstractEntity {

    @Column(name = "program_name", nullable = false, unique = true)
    private String programName;

    @Column(name = "program_hash_id", nullable = false, unique = true)
    private String programHashId;

    @Enumerated(EnumType.STRING)
    @Column(name = "program_plan")
    private ProgramPlans programPlan;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @OneToMany(mappedBy = "program")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<UsersHasProgram> usersPrograms = Sets.newHashSet();

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "card_image_url", nullable = false)
    private String cardImageUrl;

    @Column(name = "program_logo", nullable = false)
    private String programLogo;

    @Column(name = "host_url", nullable = false, unique = false)
    private String hostUrl;

    @Column(name = "notification_base_url", unique = false)
    private String notificationBaseUrl;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "wallet_required", nullable = false)
    private boolean walletRequired;

    @Column(name = "embossing")
    private String embossing;

    @Column(name = "collaterals")
    private String collaterals;

    @OneToOne
    private CorporateProcessor corporateProcessor;

    @Column(name = "business_id")
    private String businessId;

    @Column(name = "webhook_url")
    private String webhookUrl;

//    @Column
//    private String email;
//    @Column
//    private String mobile;

    @Column(name = "client_id")
    private long clientId;

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public ProgramPlans getProgramPlan() {
        return programPlan;
    }

    public void setProgramPlan(ProgramPlans programPlan) {
        this.programPlan = programPlan;
    }

    public String getProgramHashId() {
        return programHashId;
    }

    public void setProgramHashId(String programHashId) {
        this.programHashId = programHashId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCardImageUrl() {
        return cardImageUrl;
    }

    public void setCardImageUrl(String cardImageUrl) {
        this.cardImageUrl = cardImageUrl;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CorporateProcessor getCorporateProcessor() {
        return corporateProcessor;
    }

    public void setCorporateProcessor(CorporateProcessor corporateProcessor) {
        this.corporateProcessor = corporateProcessor;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getNotificationBaseUrl() {
        return notificationBaseUrl;
    }

    public void setNotificationBaseUrl(String notificationBaseUrl) {
        this.notificationBaseUrl = notificationBaseUrl;
    }

    public String getProgramLogo() {
        return programLogo;
    }

    public void setProgramLogo(String programLogo) {
        this.programLogo = programLogo;
    }

    public Set<UsersHasProgram> getUsersPrograms() {
        return usersPrograms;
    }

    public void setUsersPrograms(Set<UsersHasProgram> usersPrograms) {
        this.usersPrograms = usersPrograms;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    /*public String getEmail() {
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
    }*/

    public boolean isWalletRequired() {
        return walletRequired;
    }

    public void setWalletRequired(boolean walletRequired) {
        this.walletRequired = walletRequired;
    }

    public String getEmbossing() {
        return embossing;
    }

    public void setEmbossing(String embossing) {
        this.embossing = embossing;
    }

    public String getCollaterals() {
        return collaterals;
    }

    public void setCollaterals(String collaterals) {
        this.collaterals = collaterals;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getClientId() {
        return clientId;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Program program = (Program) o;
        return Objects.equals(programName, program.programName) &&
                Objects.equals(programHashId, program.programHashId) &&
                Objects.equals(balance, program.balance) &&
                Objects.equals(usersPrograms, program.usersPrograms) &&
                Objects.equals(currency, program.currency) &&
                Objects.equals(cardImageUrl, program.cardImageUrl) &&
                Objects.equals(programLogo, program.programLogo) &&
                Objects.equals(hostUrl, program.hostUrl) &&
                Objects.equals(businessId, program.businessId) &&
                Objects.equals(notificationBaseUrl, program.notificationBaseUrl) &&
                status == program.status &&
                Objects.equals(corporateProcessor, program.corporateProcessor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programName, programHashId, balance, usersPrograms, currency, cardImageUrl, programLogo, hostUrl, notificationBaseUrl, status, corporateProcessor, businessId);
    }


}
