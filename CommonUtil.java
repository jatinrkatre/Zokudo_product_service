package com.cards.zokudo.util;

import com.cards.zokudo.entities.Users;
import com.cards.zokudo.enums.BizErrors;
import com.cards.zokudo.enums.Status;
import com.cards.zokudo.exceptions.BizException;
import com.cards.zokudo.repositories.UsersRepository;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Slf4j
public class CommonUtil {

    public static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat dateFormatterSlash = new SimpleDateFormat("dd/MM/yyyy");
    public static SimpleDateFormat dateTimeFromatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat dateFormatterSlashWithTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    static final String SOURCE = "0123456789ABCDFGHIJKLMNOPQRSTUVWXYZ0123456789abcdfghijklmnopqrstuvwxyz";
    private static final String IPV4_REGEX = "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$";
    private static final Pattern IPv4_PATTERN = Pattern.compile(IPV4_REGEX);

    public static final String startTime = " 00:00:00";
    public static final String endTime = " 23:59:59";

    static SecureRandom secureRnd = new SecureRandom();
/*
    public static final AWSCredentials credentials = new BasicAWSCredentials(Constants.ACCESS_KEY, Constants.SECRET_KEY);
    public static final AmazonS3 s3Client = new AmazonS3Client(credentials);*/

    public static String[] getProgramAndRequestUrl(HttpServletRequest request) {
        return request.getRequestURI().split("/");
    }

    public static String getBasicAuthorization(String applicationLevelUserName, String applicationLevelUserPassword) {
        String result = "Basic ";
        String credentials = applicationLevelUserName + ":" + applicationLevelUserPassword;
        result = result + DatatypeConverter.printBase64Binary(credentials.getBytes(StandardCharsets.UTF_8));
        return result;
    }

    public static String getString(String description) {
        if (description != null) {
            return description.replaceAll("\\W", " ");
        }
        return null;
    }

    public static boolean isValidMobileNumber(String str) {
        try {
            return str.trim().matches("^(?=(?:[6-9]){1})(?=[0-9]{10}).*");
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidTitle(String str) {
        try {
            switch (str) {
                case "Mr":
                case "Ms":
                case "Mrs":
                    return true;
            }
        } catch (Exception e) {
            throw new BizException(BizErrors.NULL_ERROR.getValue(), "title should not be empty");
        }
        return false;
    }

    public static boolean isValidGender(String str) {
        try {
            switch (str) {
                case "M":
                case "F":
                    return true;
            }
        } catch (Exception e) {
            throw new BizException(BizErrors.NULL_ERROR.getValue(), "Gender should not be empty");
        }
        return false;
    }

    // date format should be like YYYY-MM-DD


    public static boolean validIdType(String idType) {
        switch (idType) {
            case "aadhaar":
            case "pan":
            case "driver_id":
                return true;
        }
        return false;
    }

    public static String generateSixDigitNumericString() {
        int number = (int) (Math.random() * 1000000);
        return String.format("%06d", number);
    }

    public static boolean validCountry(String countryOfIssue) {
        return "India".equals(countryOfIssue);
    }

    public static String randomString(int length, String preVal) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(SOURCE.charAt(secureRnd.nextInt(SOURCE.length())));
        return preVal + sb.toString().toUpperCase();
    }

    public static boolean isAlphanumeric(String str) {
        String temp = str.trim();
        return temp.matches("[A-Za-z0-9]+");
    }

    public static boolean isAlpha(String str) {
        String temp = str.trim();
        return temp.matches("[A-Za-z]+");
    }

    public static boolean isNumeric(String str) {
        try {
            return str.trim().matches("[0-9]+");
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isFloatString(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String getRandomString(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 3;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    /**
     * UPLOADING FILES TO AZURE BLOB STORAGE
     * file - file to be uploaded,
     * directory - azure container name
     * id - id to be applied to make create unique reference at azure storage
     **/
    public static String uploadImage(MultipartFile file, String id, String directory) {
        if (Objects.nonNull(file)) {
            try {
                CloudStorageAccount storageAccount;
                String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                String fileName = generateFileKey(id) + fileExtension;
                String uri = null;

                // Parse the connection string and create a blob client to interact with Blob storage
                storageAccount = CloudStorageAccount.parse(Constants.STORAGE_CONNECTION_STRING);
                CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
                CloudBlobContainer container = blobClient.getContainerReference(directory);

                // Create the container if it does not exist with public access
                container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());

                //Getting a blob reference, create a unique blob reference
                CloudBlockBlob blob = container.getBlockBlobReference(fileName);

                //Creating blob and uploading file to it
                log.info("Uploading file to Azure storage " + blob);
                blob.upload(file.getInputStream(), file.getSize());

                //Listing contents of container
                return blob.getUri().toString();
            } catch (StorageException ex) {
                log.error(String.format("Error returned from the service. Http code: %d and error code: %s", ex.getHttpStatusCode(), ex.getErrorCode()));
                throw new BizException(BizErrors.APPLICATION_ERROR.getValue(), "file upload operation failed!");
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new BizException(BizErrors.APPLICATION_ERROR.getValue(), "file upload operation failed!");
            }
        }
        return null;
    }


    public static String generateFileKey(String id) {
        try {
            String systemMillis = String.valueOf(System.currentTimeMillis());
            return id + "_" + systemMillis;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean isValidDate(String stringDate) {
        boolean result = false;
        try {
            dateFormatter.parse(stringDate);
            result = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Date parseDate(String date) throws ParseException {
        Date dateObject;
        try {
            dateObject = dateFormatter.parse(date);
        } catch (ParseException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw e;
        }
        return dateObject;
    }

    //Validation for Sql Injection
    public static boolean containsSqlKeywords(String str) {
        String[] sqlKeywords = {"select", "drop", "insert", "delete", "table", "merge", "union", "all", "dual", "group by", "having", "insert", "update", "join", "left join", "right join", "inner join"};
        String[] inputWords = str.split(" ");
        for (String inputWord : inputWords) {
            for (String sqlKeyword : sqlKeywords) {
                if (inputWord.equalsIgnoreCase(sqlKeyword)) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void generateExcelSheet(final List<String> headers, final List<Map<String, String>> dataList, String resultantFileName, HttpServletResponse response) {
        try {
            if (headers == null || headers.size() == 0) {
                return;
            }
            if (dataList == null || dataList.size() == 0) {
                return;
            }
            final XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFCellStyle cellStyle = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            cellStyle.setFont(font);

            final XSSFSheet sheet = workbook.createSheet();
            XSSFRow xssfRow = sheet.createRow(0);
            for (int i = 0; i < headers.size(); i++) {
                XSSFCell xssfCell = xssfRow.createCell(i);
                xssfCell.setCellStyle(cellStyle);
                xssfCell.setCellValue(headers.get(i));
            }
            for (int j = 0; j < dataList.size(); j++) {
                XSSFRow row = sheet.createRow((j + 1));
                Map<String, String> eachDataMap = dataList.get(j);
                for (int k = 0; k < headers.size(); k++) {
                    XSSFCell cell = row.createCell(k);
                    String key = headers.get(k);
                    String value = eachDataMap.get(key);
                    cell.setCellValue(value);
                }
            }
            response.setHeader("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader("Content-Disposition", "attachment; filename=" + resultantFileName);

            final ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return;
        }
    }

    public static Users getLoggedInUser(HttpServletRequest request, UsersRepository usersRepository, String programUrl) {
        String authorization = request.getHeader("Authorization");
        final String username = ((new String(Base64.getDecoder().decode((authorization.replaceAll("Basic ", ""))))).split(":"))[0];
        Users user = usersRepository.findByUsername(username, programUrl, Status.ACTIVE);
        return user;
    }

    public static boolean isValidInet4Address(String ipAddress) {

        if (StringUtils.isBlank(ipAddress)) return false;

        return IPv4_PATTERN.matcher(ipAddress).matches();
    }


    public static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.falconide.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(Constants.SET_FROM));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body, "UTF-8", "html");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }

    }

}
