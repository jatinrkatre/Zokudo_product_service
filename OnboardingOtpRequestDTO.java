package com.cards.zokudo.dto.request;

import lombok.Data;

@Data
public class OnboardingOtpRequestDTO {

    private String otp;
    private String commercialHashId;
    private String userHashId;

}
