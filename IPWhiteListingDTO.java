package com.cards.zokudo.dto.request;

import com.cards.zokudo.entities.Users;
import lombok.Data;

@Data
public class IPWhiteListingDTO {

    private String ipAddress;
    private long userId;
    private Users user;
    private String status;
    private long id;

}
