package com.cards.zokudo.services;

import com.cards.zokudo.dto.request.DistributorDTO;
import com.cards.zokudo.entities.DistributorEntity;
import com.cards.zokudo.response.ApiResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface DistributorService {

	ApiResponse<Object> onBoardDistributor(DistributorDTO dto, HttpServletRequest request, String programUrl);

    Object distributorList(DistributorDTO dto,String role,String programUrl);

	Object getDistributorById(String distributorId);

	ApiResponse<Object> updateDistributor(DistributorDTO dto, String programUrl);

	Object getDistributorByProgramHash(String programUrl);

	Object getDistributorList(String programUrl, String role,String loggedInUserHashId);

	DistributorEntity getDistribtorByUserHashId(String userHashId);

	void distributorListForDownload(Map<String, String> requestParams,HttpServletRequest request, HttpServletResponse response, String programUrl);

}
