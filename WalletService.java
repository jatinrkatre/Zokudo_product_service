package com.cards.zokudo.services;

import com.cards.zokudo.dto.response.CommonServiceResponse;
import com.cards.zokudo.services.program.persist.ProgramRequestDTO;

public interface WalletService {

    CommonServiceResponse createDefaultPocket(final long programId, final long clientId, String programUrl);

    void addCurrencyWithClient(long clientId, ProgramRequestDTO programDTO, String programUrl);

    void createClientAccount(long clientId, String programUrl);

    void addDefaultFeesForClient(long clientId, long programId, String programUrl);

    double getClientAccountBalances(String clientId, String programUrl);

    double getDistributorAccountBalances(String clientId, String programUrl, String loggedInUserRole, String loggedInUserHashId);

	void addDefaultRevenueForProgram(long clientId, long programId, String programUrl);
	
	void addDefaultComm(long distributorId, long programId, String programUrl);
	
	void addDefaultRetailerComm(long retailerId, long programId, String programUrl);

    void createAgentAccount(long agentId, long clientId, String programUrl);

    void addDefaultClientCommissionForClientByProgram(long clientId, long programId, String programUrl);

    void addDefaultClientDiscountForClientByProgram(long clientId, long programId, String programUrl);

    void addDefaultRetailerDiscount(long retailerId, long programId, String programUrl);
}
