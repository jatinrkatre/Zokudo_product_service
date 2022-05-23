package com.cards.zokudo.entities;


import com.cards.zokudo.enums.BalanceTypeAtProcessor;
import com.cards.zokudo.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.glassfish.jersey.internal.guava.Sets;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "processor")
public class Processor extends AbstractEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "balanceType_Processor", nullable = false)
    private BalanceTypeAtProcessor balanceTypeAtProcessor;

    @ManyToMany
    @JoinTable(name = "processor_has_cardtype", joinColumns = {@JoinColumn(name = "processor_id",
            referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "card_type_id", referencedColumnName = "id")})
    private Set<CardType> processorCardType = Sets.newHashSet();


    @ManyToMany
    @JoinTable(name = "processor_has_issuer", joinColumns = {@JoinColumn(name = "processor_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "issuer_id", referencedColumnName = "id")})
    private Set<Issuer> processorIssuer = Sets.newHashSet();


    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "cardProcessor")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private final Set<CorporateProcessor> corporateProcessor = Sets.newHashSet();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<CardType> getProcessorCardType() {
        return processorCardType;
    }

    public void setProcessorCardType(Set<CardType> processorCardType) {
        this.processorCardType = processorCardType;
    }

    public Set<Issuer> getProcessorIssuer() {
        return processorIssuer;
    }

    public void setProcessorIssuer(Set<Issuer> processorIssuer) {
        this.processorIssuer = processorIssuer;
    }

    public BalanceTypeAtProcessor getBalanceTypeAtProcessor() {
        return balanceTypeAtProcessor;
    }

    public void setBalanceTypeAtProcessor(BalanceTypeAtProcessor balanceTypeAtProcessor) {
        this.balanceTypeAtProcessor = balanceTypeAtProcessor;
    }
}
