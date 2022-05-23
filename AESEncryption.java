package com.cards.zokudo.util;

import com.cards.zokudo.enums.BizErrors;
import com.cards.zokudo.exceptions.BadRequestException;
import com.cards.zokudo.exceptions.BizException;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

@Slf4j
public class AESEncryption {

    public static String secretKey = "FB11C9EE72F91DE312AE6312F9E678F90CA49D24";
    public static String passEncoderKey = "FB11C9EE72F91DE312AE6312F9E678F90CA49D24";
    private static String salt = "MTU2ODkwMTI0MjExNVBSR01DMks0S1JEUkFE";

    private static final String BASE64_ENCODED_PRIVATE_KEY = "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";


    public static String encrypt(String strToEncrypt, String secret) {
        try {
            byte[] iv = {'C', 'h', 'A', 'c', 'H', 'a', 'v', 'I', 'd', 'H', 'a', 'Y', 'a', 'k', 'H', 'h'};
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            log.info("Exception occurred while AES encryption ", e.getCause());
            //log.error("error while response encryption...{}", strToEncrypt);
            throw new BizException("invalid request!no data found");
        }
    }
    // AES Password decoder coming from javascript for user and admin login

    public static String getDecodedPassword(String password) {
        try {
            String secret = passEncoderKey;
            String cipherText = password;
            byte[] cipherData = Base64.getDecoder().decode(cipherText);
            byte[] saltData = Arrays.copyOfRange(cipherData, 8, 16);
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            final byte[][] keyAndIV = GenerateKeyAndIV(32, 16, 1, saltData, secret.getBytes(StandardCharsets.UTF_8), md5);
            SecretKeySpec key = new SecretKeySpec(keyAndIV[0], "AES");
            IvParameterSpec iv = new IvParameterSpec(keyAndIV[1]);
            byte[] encrypted = Arrays.copyOfRange(cipherData, 16, cipherData.length);
            Cipher aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
            aesCBC.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] decryptedData = aesCBC.doFinal(encrypted);
            String decryptedText = new String(decryptedData, StandardCharsets.UTF_8);
            return decryptedText;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[][] GenerateKeyAndIV(int keyLength, int ivLength, int iterations, byte[] salt, byte[] password,
                                             MessageDigest md) {

        int digestLength = md.getDigestLength();
        int requiredLength = (keyLength + ivLength + digestLength - 1) / digestLength * digestLength;
        byte[] generatedData = new byte[requiredLength];
        int generatedLength = 0;

        try {
            md.reset();
            while (generatedLength < keyLength + ivLength) {
                if (generatedLength > 0)
                    md.update(generatedData, generatedLength - digestLength, digestLength);
                md.update(password);
                if (salt != null)
                    md.update(salt, 0, 8);
                md.digest(generatedData, generatedLength, digestLength);
                for (int i = 1; i < iterations; i++) {
                    md.update(generatedData, generatedLength, digestLength);
                    md.digest(generatedData, generatedLength, digestLength);
                }
                generatedLength += digestLength;
            }
            byte[][] result = new byte[2][];
            result[0] = Arrays.copyOfRange(generatedData, 0, keyLength);
            if (ivLength > 0)
                result[1] = Arrays.copyOfRange(generatedData, keyLength, keyLength + ivLength);
            return result;
        } catch (DigestException e) {
            throw new RuntimeException(e);
        } finally {
            Arrays.fill(generatedData, (byte) 0);
        }
    }

    public static String getRsaDecryptedData(final String input) {
        try {
            final Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, getRsaPrivateKey(BASE64_ENCODED_PRIVATE_KEY));
            return new String(cipher.doFinal(Base64.getDecoder().decode(input)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            // log.error(e.getMessage(), e);
            throw new BadRequestException(BizErrors.BAD_REQUEST_ERROR.getValue() , "error!while decryption password");
        }
    }

    private static PrivateKey getRsaPrivateKey(final String base64EncodedPrivateKey) {
        try {
            PrivateKey privateKey = null;
            final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
                    Base64.getDecoder().decode(base64EncodedPrivateKey.getBytes()));
            KeyFactory keyFactory = null;
            keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(keySpec);
            return privateKey;
        } catch (NoSuchAlgorithmException e) {
//            log.error(e.getMessage(), e);
//            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
//                    "NoSuchAlgorithmException!", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (InvalidKeySpecException e) {
//            log.error(e.getMessage(), e);
//            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
//                    "InvalidKeySpecException!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

}
