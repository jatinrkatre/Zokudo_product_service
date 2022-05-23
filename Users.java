package com.cards.zokudo.entities;

import com.cards.zokudo.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.glassfish.jersey.internal.guava.Sets;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users", indexes = {
        @Index(name = "user_hash_id", columnList = "user_hash_id"),
        @Index(name = "mobile", columnList = "mobile"),
        @Index(name = "email", columnList = "email"),
        @Index(name = "username", columnList = "username")
})
public class Users extends AbstractEntity {

    @Column(name = "user_hash_id", nullable = false, unique = true)
    private String userHashId;

    @Column(name = "mobile", nullable = false)
    private String mobile;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "mobile_token")
    private String mobileToken;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "channel_id", nullable = false)
    private String channelId;

    @Column(name = "blocked")
    private boolean blocked;

    @Temporal(TemporalType.TIMESTAMP)
    private Date otpGenerationTime;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_has_role", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> userRoles = Sets.newHashSet();

    @OneToMany(mappedBy = "users")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<UsersHasProgram> usersPrograms = Sets.newHashSet();

    @Column(name = "product_type")
    private String productType;

    @Column(name = "product_code")
    private String productCode;

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Privilege)) {
            return false;
        }
        Privilege privilege = (Privilege) object;
        return this.hashCode() == privilege.hashCode();
    }

    public String getUserHashId() {
        return userHashId;
    }

    public void setUserHashId(String userHashId) {
        this.userHashId = userHashId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String firstName) {
        this.fullName = firstName;
    }

    /*public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }*/

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileToken() {
        return mobileToken;
    }

    public void setMobileToken(String mobileToken) {
        this.mobileToken = mobileToken;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Set<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<Role> userRoles) {
        this.userRoles = userRoles;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Date getOtpGenerationTime() {
        return otpGenerationTime;
    }

    public void setOtpGenerationTime(Date otpGenerationTime) {
        this.otpGenerationTime = otpGenerationTime;
    }

    public Set<UsersHasProgram> getUsersPrograms() {
        return usersPrograms;
    }

    public void setUsersPrograms(Set<UsersHasProgram> usersPrograms) {
        this.usersPrograms = usersPrograms;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }
}
