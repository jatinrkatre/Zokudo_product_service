package com.cards.zokudo.entities;


import com.cards.zokudo.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "privilege")
public class Privilege extends AbstractEntity {

    private static final long serialVersionUID = -995255552931676320L;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name= "description")
    private String desc;
    
    
    public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "privileges", fetch = FetchType.EAGER)
    private Set<Role> privilegeRoles = new HashSet<Role>();

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Privilege)) {
            return false;
        }
        Privilege privilege = (Privilege) object;
        return this.hashCode() == privilege.hashCode();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Role> getPrivilegeRoles() {
        return privilegeRoles;
    }

    public void setPrivilegeRoles(Set<Role> privilegeRoles) {
        this.privilegeRoles = privilegeRoles;
    }

	@Override
	public String toString() {
		return "Privilege [name=" + name + ", status=" + status + ", privilegeRoles=" + privilegeRoles + "]";
	}
    
}
