package com.cards.zokudo.dto.request;

import lombok.Data;

@Data
public class WebhookUrlDto {
    private String clientId;
    private long programId;
    private String webhookUrl;
}
