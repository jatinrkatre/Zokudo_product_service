package com.cards.zokudo.dto.request;

import lombok.Data;

@Data
public class CorporateProcessorFilterDto {
    String dateRange;
    String corporateName;
    String corporateCode;
    String processorName;
}
