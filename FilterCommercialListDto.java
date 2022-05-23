package com.cards.zokudo.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import com.cards.zokudo.enums.Status;

@Data
public class FilterCommercialListDto {

	 String dateRange;
	 Long clientCode;
	 Status commercialStatus;
}
