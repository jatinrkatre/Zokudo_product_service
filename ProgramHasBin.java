package com.cards.zokudo.entities;


import javax.persistence.*;

@Entity
@Table(name="program_has_bin", indexes = {
        @Index(name = "program_id", columnList = "program_id")
})
public class ProgramHasBin extends AbstractEntity {

    @Column(name="bin_number" , nullable = false)
    private String binNumber;

    @Column(name="program_id" , nullable = false)
    private Long programId;

    @Column(name="program_type" , nullable = false)
    private String programType;

    public String getBinNumber() {
        return binNumber;
    }

    public void setBinNumber(String binNumber) {
        this.binNumber = binNumber;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public String getProgramType() { return programType; }

    public void setProgramType(String programType) { this.programType = programType; }

}
