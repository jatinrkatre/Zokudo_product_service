/*
 * InstaRem Overseas Money Transfer.
 * https://www.instarem.com/en-in/
 *
 * Copyright (c) 2014-2019 InstaReM
 *
 * InstaRem is an acronym of Instant Remittance.
 * InstaRem Software is designed and developed to ease the Overseas Money Transfer.
 * It allows you to transfer your money overseas with minimum cost and time.
 *
 *
 * This file is licensed and cannot be accessed by outside InstaRem.
 * It can only be accessed and modified by the authorized InstaRem Technical Teams.
 * If any unauthorized, outside of the InstaRem, user found to be unlawfully modified
 * the content of this file,  will be prosecuted under the Copyright Act
 *
 */

package com.cards.zokudo.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "url_identifier")
@Data
public class URLIdentifier extends AbstractEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "url")
    private String url;

    @Column(name = "url_name")
    private String urlName;

    @Column(name = "is_external_url")
    private boolean isExternalUrl = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "privilege_id", referencedColumnName = "id")
    private Privilege privilege;
}
