package com.cards.zokudo.entities;

import com.cards.zokudo.enums.Status;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ip_white_listing")
@Data
public class IPWhiteListing extends AbstractEntity {

    @Column(name = "ip_address", unique = true, nullable = false)
    private String ipAddress;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "user_id")
    private long userId;

    private String userEmail;

    private String contact;

}
