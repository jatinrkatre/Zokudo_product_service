package com.cards.zokudo.dto.request;

import com.cards.zokudo.enums.ProgramPlans;
import com.cards.zokudo.enums.ProgramTyps;
import lombok.Data;

@Data
public class ProgramResponseDTO {

    private String programId;

    private String programName;

    private ProgramPlans programPlan;

    private String programTyps;
}
