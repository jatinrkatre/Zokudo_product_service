package com.cards.zokudo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.glassfish.jersey.internal.guava.Sets;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="BIN_RANGE", indexes = {
        @Index(name = "processor_id", columnList = "processor_id"),
        @Index(name = "program_type", columnList = "program_type")
})
public class BinRange extends AbstractEntity{

    @Column(name="bin" , nullable = false)
    private String bin;

    @Column(name="processor_id" , nullable = false)
    private String processorId;

    @Column(name="program_type" , nullable = false)
    private String programType;

    @OneToMany(mappedBy = "programName")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Program> programs = Sets.newHashSet();

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getProcessorId() {
        return processorId;
    }

    public void setProcessorId(String processorId) {
        this.processorId = processorId;
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public Set<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(Set<Program> programs) {
        this.programs = programs;
    }
}
