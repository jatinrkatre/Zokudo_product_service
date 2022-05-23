package com.cards.zokudo.entities;


import com.cards.zokudo.enums.Status;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "corporate_processor", indexes = {
        @Index(name = "status", columnList = "status")
})
public class CorporateProcessor extends AbstractEntity {


    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "processor_id", referencedColumnName = "id")
    private Processor cardProcessor;

    @Column(name = "zaggle_company_id")
    private String zaggleCompanyId;

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

    public Processor getCardProcessor() {
        return cardProcessor;
    }

    public void setCardProcessor(Processor cardProcessor) {
        this.cardProcessor = cardProcessor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
