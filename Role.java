package com.cards.zokudo.entities;



import com.cards.zokudo.enums.Roles;
import com.cards.zokudo.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import org.glassfish.jersey.internal.guava.Sets;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="role")
public class Role extends AbstractEntity {

    @Column(name="name" , nullable = false)
    private String name;

    @Column(name="status" , nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

	@Column(name="category")
    @Enumerated(EnumType.STRING)
    private Roles category;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "userRoles")
    private Set<Users> users = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name="privilege_has_role", joinColumns = {@JoinColumn (name="role_id" , referencedColumnName = "id")} ,
    inverseJoinColumns = {@JoinColumn(name="privilege_id" , referencedColumnName = "id")})
    private Set<Privilege> privileges = Sets.newHashSet();

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

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }
    public Roles getCategory() {
  		return category;
  	}

  	public void setCategory(Roles category) {
  		this.category = category;
  	}
}
