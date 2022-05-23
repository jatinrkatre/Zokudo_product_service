package com.cards.zokudo.entities;


import com.cards.zokudo.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.glassfish.jersey.internal.guava.Sets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "issuer")
public class Issuer extends AbstractEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @ManyToMany(mappedBy = "processorIssuer")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Processor> cardProcessor = Sets.newHashSet();

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

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
}
