
package com.cards.zokudo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlMetaData {

    public static final String dynamicUrl = "{}/api/";
    public final String REGISTER_CORPORATE_AT_ZAGGLE;
    public final String AUTHENTICATE_AND_AUTHORIZE_USER;
    public final String ADD_DEFAULT_POCKET;
    public final String ADD_CURRENCY;
    public final String ADD_CLIENT_ACCOUNT;
    public final String ADD_DEFAULT_FEES;
    public final String GET_CLIENT_ACCOUNT_BALANCES;
    public final String GET_DISTRIBUTOR_ACCOUNT_BALANCES;
    public final String ADD_DEFAULT_REVENUE;
    public final String ADD_DEFAULT_COMM;
    public final String ADD_DEFAULT_RETAILER_COMM;
    public final String ADD_AGENT_ACCOUNT;
    public final String ADD_DEFAULT_CLIENT_COMMISSION;
    public final String ADD_DEFAULT_CLIENT_DISCOUNT;
    public final String ADD_DEFAULT_RETAILER_DISCOUNT;
    public final String CREATE_SOR_PROGRAM;

    public UrlMetaData(@Value(value = "${version}") String apiVersion,
                       @Value(value = "${url.context.authcontext}") String AUTH_CONTEXT,
                       @Value("${url.context.zagglecontext}") String ZAGGLE_CONTEXT,
                       @Value(value = "${url.context.walletContext}") String walletContext,
                       @Value(value = "${url.context.sorcontext}") String sorContext) {
        AUTH_CONTEXT = AUTH_CONTEXT + apiVersion;
        this.AUTHENTICATE_AND_AUTHORIZE_USER = AUTH_CONTEXT + "/authentication/authrequest";
        this.REGISTER_CORPORATE_AT_ZAGGLE = ZAGGLE_CONTEXT + dynamicUrl + apiVersion + "/registerCompany";
        this.ADD_DEFAULT_POCKET = walletContext + dynamicUrl + apiVersion + "/wallet/addDefaultPocket";
        this.ADD_CURRENCY = walletContext + dynamicUrl + apiVersion + "/wallet/currency/add";
        this.ADD_CLIENT_ACCOUNT = walletContext + dynamicUrl + apiVersion + "/wallet/client_account/add/default/";
        this.ADD_DEFAULT_FEES = walletContext + dynamicUrl + apiVersion + "/feemanagement/adddefault";
        this.GET_CLIENT_ACCOUNT_BALANCES = walletContext + dynamicUrl + apiVersion + "/wallet/client_account/%s/accountBalance";
        this.ADD_DEFAULT_REVENUE = walletContext + dynamicUrl + apiVersion + "/revenueMngnt/addDefaultRevenue";
        this.ADD_DEFAULT_COMM = walletContext + dynamicUrl + apiVersion + "/commmission/distributor/addDefaultComm";
        this.ADD_DEFAULT_RETAILER_COMM = walletContext + dynamicUrl + apiVersion + "/commmission/retailer/addDefaultRetailerComm";
        this.ADD_AGENT_ACCOUNT = walletContext + dynamicUrl + apiVersion + "/wallet/openAgentAccount";
        this.GET_DISTRIBUTOR_ACCOUNT_BALANCES = walletContext + dynamicUrl + apiVersion + "/wallet/getTotalPoolBalance";
        this.ADD_DEFAULT_CLIENT_COMMISSION = walletContext + dynamicUrl + apiVersion + "/commission/client/addDefaultClientCommission";
        this.ADD_DEFAULT_CLIENT_DISCOUNT = walletContext + dynamicUrl + apiVersion + "/commission/client/addDefaultClientDiscount";
        this.ADD_DEFAULT_RETAILER_DISCOUNT = walletContext + dynamicUrl + apiVersion + "/commmission/retailer/addDefaultRetailerDiscount";
        this.CREATE_SOR_PROGRAM = sorContext + dynamicUrl + apiVersion + "/program/add";
    }
}
