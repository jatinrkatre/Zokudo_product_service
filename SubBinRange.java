package com.cards.zokudo.entities;

import javax.persistence.*;

@Entity
@Table(name="SUB_BIN_RANGE", indexes = {
        @Index(name = "sub_bin", columnList = "sub_bin")
})
public class SubBinRange extends AbstractEntity {

    @Column(name="sub_bin" , nullable = false)
    private String subBin;

    @Column(name="card_type" , nullable = false)
    private String cardType;

    @Column(name="status")
    private String status;
    
    public String getSubBin() {
        return subBin;
    }

    public void setSubBin(String subBin) {
        this.subBin = subBin;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
