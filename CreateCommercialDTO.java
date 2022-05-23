package com.cards.zokudo.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateCommercialDTO {

    private String fixed;
    private String oneTimeFee;
    private String revenueSharing;
    private String monthlyMaintenance;
    private String physicalCardCost;
    private String virtualCardCost;
    private String remarks;
    private String programHashId;
    private MultipartFile commercialDocument;
    private String HashId;
    private String clientId;
}
