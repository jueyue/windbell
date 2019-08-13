/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package cn.afterturn.boot.core.crypto;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA2工具类
 *
 * @author shaoliang
 */
public class RsaUtil {

    private static final String SIGN_TYPE_RSA = "RSA";

    private static final String PADDING = "RSA/ECB/PKCS1Padding";

    private static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    private static final String CHAR_SET          = "UTF-8";
    /**
     * RSA最大加密明文大小
     */
    private static final int    MAX_ENCRYPT_BLOCK = 255;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 256;
    /**
     * 密钥长度
     */
    private static final int KEY_SIZE          = 2048;

    public enum KEY {
        /**
         * 公钥
         */
        PUBLICKEY,
        /**
         * 私钥
         */
        PRIVATEKEY
    }

    /**
     * 生成公钥私钥
     */
    public static Map<KEY, String> getRSAKeys() throws Exception {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(SIGN_TYPE_RSA);

        keyPairGenerator.initialize(KEY_SIZE);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        RSAPublicKey  rsaPublicKey  = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<KEY, String> map = new HashMap<KEY, String>();

        map.put(KEY.PUBLICKEY, Base64.encodeBase64String(rsaPublicKey.getEncoded()));
        map.put(KEY.PRIVATEKEY, Base64.encodeBase64String(rsaPrivateKey.getEncoded()));

        return map;
    }

    /**
     * 签名
     */
    public static String rsa256Sign(final String content, final String privateKey) {

        try {

            PrivateKey priKey = getPrivateKeyFromPKCS8(SIGN_TYPE_RSA, new ByteArrayInputStream(privateKey.getBytes()));

            Signature signature = Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);

            signature.initSign(priKey);

            signature.update(content.getBytes(CHAR_SET));

            return Base64.encodeBase64String(signature.sign());

        } catch (Exception e) {
            throw new RuntimeException("RSAcontent = " + content, e);
        }

    }

    /**
     * 验证签名
     */
    public static boolean rsa256CheckContent(final String content, final String sign, final String publicKey) {
        try {

            PublicKey pubKey = getPublicKeyFromX509("RSA", new ByteArrayInputStream(publicKey.getBytes()));

            Signature signature = Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);

            signature.initVerify(pubKey);

            signature.update(content.getBytes(CHAR_SET));

            return signature.verify(Base64.decodeBase64(sign));

        } catch (Exception e) {
            throw new RuntimeException("RSAcontent = " + content + ",sign=" + sign, e);
        }
    }

    /**
     * 解密
     */
    public static String rsaDecrypt(final String content, final String privateKey) throws Exception {
        try {
            PrivateKey priKey = getPrivateKeyFromPKCS8(SIGN_TYPE_RSA,
                    new ByteArrayInputStream(privateKey.getBytes()));
            Cipher cipher = Cipher.getInstance(PADDING);
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            byte[]                encryptedData = Base64.decodeBase64(content.getBytes(CHAR_SET));
            int                   inputLen      = encryptedData.length;
            ByteArrayOutputStream out           = new ByteArrayOutputStream();
            int                   offSet        = 0;
            byte[]                cache;
            int                   i             = 0;
            // 对数据分段解密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }
            byte[] decryptedData = out.toByteArray();
            out.close();

            return new String(decryptedData, CHAR_SET);
        } catch (Exception e) {
            throw new Exception("EncodeContent = " + content, e);
        }
    }

    /**
     * 加密
     */
    public static String rsaEncrypt(final String content, final String publicKey) throws Exception {
        try {
            PublicKey pubKey = getPublicKeyFromX509(SIGN_TYPE_RSA,
                    new ByteArrayInputStream(publicKey.getBytes()));
            Cipher cipher = Cipher.getInstance(PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[]                data     = content.getBytes(CHAR_SET);
            int                   inputLen = data.length;
            ByteArrayOutputStream out      = new ByteArrayOutputStream();
            int                   offSet   = 0;
            byte[]                cache;
            int                   i        = 0;
            // 对数据分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedData = Base64.encodeBase64(out.toByteArray());
            out.close();

            return new String(encryptedData, CHAR_SET);
        } catch (Exception e) {
            throw new Exception("EncryptContent = " + content, e);
        }
    }

    /**
     * 获取私钥
     */
    private static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream ins) throws Exception {
        if (ins == null || StringUtils.isEmpty(algorithm)) {
            return null;
        }

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        byte[] encodedKey = readText(ins).getBytes();

        encodedKey = Base64.decodeBase64(encodedKey);

        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }

    /**
     * 获取公钥
     */
    private static PublicKey getPublicKeyFromX509(String algorithm, InputStream ins) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        StringWriter writer = new StringWriter();

        io(new InputStreamReader(ins), writer, -1);

        byte[] encodedKey = writer.toString().getBytes();

        encodedKey = Base64.decodeBase64(encodedKey);

        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }

    private static String readText(InputStream ins) throws IOException {
        Reader       reader = new InputStreamReader(ins);
        StringWriter writer = new StringWriter();

        io(reader, writer, -1);
        return writer.toString();
    }

    private static void io(Reader in, Writer out, int bufferSize) throws IOException {
        if (bufferSize == -1) {
            bufferSize = DEFAULT_BUFFER_SIZE >> 1;
        }

        char[] buffer = new char[bufferSize];
        int    amount;

        while ((amount = in.read(buffer)) >= 0) {
            out.write(buffer, 0, amount);
        }
    }

    public static void main(String[] args) {
        try {
            String a = RsaUtil.rsaEncrypt("123456", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAptmG3qi04IbN258RoYuJiOxJ7t8xpy5JiA9VTbc6qOEZKkocuVT0L8mpX63q8q7H9XhJ4zOe7MrxKvqBnLgyrDOdtuaZb2g3NqIhM3Nu4VhKhSvrRmGjA8QAvD7plqpRjPYeDJGfldrb5u0eFeFWtTohKYv4Q6B7yVdNyu1HrCre9OanyKYth+vCgG7ihu/2htUeFmE9Ownwlx3ZMEf4BcO3lPNmI7Hgb/c9+BIwy0OVF3mxBSwnFMxR3eQiMAJjebdf4mUbVcjTtDdkaFE8b9JuLWG72bxN7UdXao/xfvtoJw1scnh2LE0m1mZNPLXJCucdSX4FHrBTv7BuNUnCVwIDAQAB");
            System.out.println("args = [" + a + "]");

            a = RsaUtil.rsaDecrypt("W+Oj9DbjizTqZupT5J6BQ9l2yWFBVXKJ8Sr9pBETXOw+YvhUvVRDTJrvD2412oES85ZpP4XRsM6bKg0JzYOl+GbW2W23Y/kQPnLgEnhfPqFKcUArL6a9iCEthIG2xviM+5X8qzLkb/w0KGwKutZdWdnforHBH7SfxGfQ9FgiCqNuG9np4NOg3S8HsvVa5KLlsEAyKZH3ANYifp6YAvyecX9CwZBQy3J8KCD5nMHbYZAXDrEa/jan3sSYTZk6bnTa9VbbHGfEbwc+h3zMxnUH0VGHQZ6OyYPFnsKPI6b6dE+DD+vBfpFix+G5N+h9H9M+1Q2GMS6ng1Yv5HBexAhdJQ==",
                    "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCm2YbeqLTghs3bnxGhi4mI7Enu3zGnLkmID1VNtzqo4RkqShy5VPQvyalfreryrsf1eEnjM57syvEq+oGcuDKsM5225plvaDc2oiEzc27hWEqFK+tGYaMDxAC8PumWqlGM9h4MkZ+V2tvm7R4V4Va1OiEpi/hDoHvJV03K7UesKt705qfIpi2H68KAbuKG7/aG1R4WYT07CfCXHdkwR/gFw7eU82YjseBv9z34EjDLQ5UXebEFLCcUzFHd5CIwAmN5t1/iZRtVyNO0N2RoUTxv0m4tYbvZvE3tR1dqj/F++2gnDWxyeHYsTSbWZk08tckK5x1JfgUesFO/sG41ScJXAgMBAAECggEBAKRW54/Sbe4k+QiUAgKy9PHHgv7kGf0IC2cXazcT/ZQvuuC7hNVLBwU2xTw3io4kd1GhfG1r/+JN9gMWIKnjiA2y/WoUchaYeKG4SalqzlDo7OWEn8WAD9d6rBq+gMdk1NRZ6rkIij5JBoXLEfINNKFmMxnAAvVhUeu7sSTqV8kMCkIHpJeAm25vo3YJQuXWTaa+xzYClse72v3cDpofxc/hWyrfNQkNmrW5WjuJjzTE4VDOIYJyWyAALVrwpLN23nwz5mZ9m+NGmpXmRvVUhvEeNZn0z7eK5j7UJzHwHlJeBhwhDnPLLgtNF8zNIUov9aRYWdbqPsf7gM1V7ZNgunECgYEA6+Y0EknFrGV0J0ziRrDUV80x0C9dYbWgN2a2OpsjhShQ0leBY8L2FwONP8tzosDW2B9q/kKZG5AvWSrIgaufEOoOVXcBWtUawqMrQ0xZ9qL3+/+AF1paZf9OoRGgJJO1MXd7NGHk95/NrYTvc7fQM6Q2pueN+NcV+nq39sJS2o8CgYEAtREb9lVUck5edIgWKCPZiv6sgFVERI89595LpHmiOuRumKaG9A5eO7LGqMQbo5P221EkA3V/njKhObYiRXDZk5ziig0U4KR1PIrS0b37KoIzDWLmOXQpHJ33e5fHR8Bn7OuX/7jwqzbf4y7m77LragFpy0peRrQrzB8KzRUln7kCgYBnmpf9/DZ766bi58ptP5ZS5YTeKR87HZi7HDombSJhhf5REyt5lBOesemkPgeQUF9LVikYeElkKKQS5k5fXO+kbxdSg8ythvCU0TLcALl2L8b3/xPTrdQL/eAbr3EE/3ZRvmb275OvCZk8fyjnSrB/Lbzu+r2Ir3YwpxsKnffjkwKBgHbByf0+6iwuinUT7LRXDw/DCZECW8w7xvaVLjeqL2VA/ejRECJyagvtra5zMU2QdpBSH7TFE/bGbkhT1RaGda4Db/Hrso3ET8BtrOg0GtA+pQ1amHWGYpCKaw989Q9yerdBrmfi/LbPPvjagk0kpJLaanETTdJByZgPpB3uiMbBAoGAO00lZ8kzwQzSszGP8gTjMGFZrgmzjyGTQUTkN4khaUIFrMgc7Hpy42nLUC1YLkylCOOLerxcJDuq0GdRgJFZCQbAM+W5zOQbhtyvbo32BcsNwfBSL4YO1j94fNdsCk9Bv0R7sSPBdvnQrQzMag41OVk7HLkNlrWEyzvUd+UBYPA=");
            System.out.println("args = [" + a + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}