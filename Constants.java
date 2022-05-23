package com.cards.zokudo.util;

import java.util.regex.Pattern;

public class Constants {

    public static final int DEFAULT_FILE_SIZE_IN_MB = 2 * 1024 * 1024;

    public static final Pattern PASSPATTERN = Pattern
            .compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

    public static final String STORAGE_CONNECTION_STRING = "DefaultEndpointsProtocol=https;" +
            "AccountName=blobmsewa;" +
            "AccountKey=ngYBCnZF6d/Bp+zRdb0JTcyCo8ZmhSjcU4OSCxhcxeLKYc90pX5H1Mw8mPcMyb2Tgw/yEEvBJ/BNDix6nzXqgQ==;";

    public static final String PROGRAM = "PROG";

    public static final String COMMERCIAL = "COMMERCIAL";

    public static final String CARD_IMAGE_DIR = "cardimage/";
    //public static final String CARD_IMAGE_DIR = "home/Pardeep_MSS/Product/card_img/";

    public static final String PROGRAM_LOGO_DIR = "programlogo/";
    //public static final String PROGRAM_LOGO_DIR = "home/Pardeep_MSS/Product/card_img/";

    public static final String CLINET_LOGO_DIR = "clientlogo";

    //    public static final String CLINET_DOCUMENT_DIR = "client_documents/";
    public static final String CLINET_DOCUMENT_DIR = "clientdocuments";

    public static final String DIST_DOCUMENT_DIR = "clientdocuments";

    public static final String AGENT_DOCUMENT_DIR = "clientdocuments";

    public static final String EMAIL_ID_FOR_ACTIVATION_AND_OTP = "zokudo";

    public static final String EMAIL_ID_PASSWORD_FOR_ACTIVATION_AND_OTP = "Z0kud@14";

    public static final String SET_FROM = "support@zokudo.com";

    public static final String[] RECIPIENTS_FOR_OTP_ACTIVATION = {"nbisane@msewa.com", "ggupta@msewa.com", "malekhya@msewa.com", "prashant@msewa.com"};

    public static final String SUBJECT_FOR_OTP_ACTIVATION = "{CLIENT_ NAME} On-boarding Verification";

    public static final String urlEscapeConstant = "\\{\\}";

}
