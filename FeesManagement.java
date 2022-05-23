package com.cards.zokudo.entities;

import com.cards.zokudo.enums.FeeFrequency;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "fees_management")
public class FeesManagement extends AbstractEntity {

    @Column(name = "fee_name")
    private String feeName;

    @Column(name = "fee_frequency")
    @Enumerated(EnumType.STRING)
    private FeeFrequency feeFrequency;

    @Column(name = "fee_value", precision = 19, scale = 4)
    private double feeValue;

    @Column(name = "is_fixed")
    private boolean isFixed;

    @Column(name = "program_id")
    private long programId;

}
