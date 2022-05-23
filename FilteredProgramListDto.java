package com.cards.zokudo.dto.request;

import lombok.Data;

@Data
public class FilteredProgramListDto {
    String dateRange;
    String programName;
    String programHashId;
    String processorName;
    String businessType;
    Long corporateProcessorId;
    Long clientCode;
}
