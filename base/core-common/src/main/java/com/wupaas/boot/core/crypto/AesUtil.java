/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.wupaas.boot.core.crypto;

import cn.hutool.core.codec.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

/**
 * AES工具类
 *
 * @author shaoliang
 */
public class AesUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(AesUtil.class);

    /**
     * 转换码
     */
    private static final String TRANSFORMATION = "AES/CFB/PKCS5Padding";
    /**
     * 字符集
     */
    private static final String CHAR_SET       = "UTF-8";

    public static String generateKey() throws Exception {

        KeyGenerator kgen = KeyGenerator.getInstance("AES");

        // 最长可以是256,但是256需要jre更新一个文件才能支持.
        kgen.init(128);

        SecretKey skey = kgen.generateKey();

        return Base64.encode(skey.getEncoded());
    }

    /**
     * 加密
     */
    public static String encrypt(final String str, final String keyStr, final String iv) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, getKey(keyStr), getIv(iv));
            final byte[] encryptData = cipher.doFinal(str.getBytes(CHAR_SET));
            return Base64.encode(encryptData);
        } catch (Exception e) {
            throw new RuntimeException("AES encrypt error", e);
        }
    }

    /**
     * 解密
     */
    public static String decrypt(final String str, final String keyStr, final String iv) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);

            cipher.init(Cipher.DECRYPT_MODE, getKey(keyStr), getIv(iv));
            final byte[] decryptData = cipher.doFinal(Base64.decode(str.getBytes(CHAR_SET)));
            return new String(decryptData, CHAR_SET);
        } catch (Exception e) {
            throw new RuntimeException("AES decrypt error", e);
        }
    }

    /**
     * 文件加密
     */
    public static void encryptFileAES(File in, File out, final String keyStr, final String iv) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, getKey(keyStr), getIv(iv));
            FileInputStream    is = new FileInputStream(in);
            CipherOutputStream os = new CipherOutputStream(new FileOutputStream(out), cipher);
            writeFile(is, os);
        } catch (Exception e) {
            throw new RuntimeException("AES encryptFile error", e);
        }
    }

    /**
     * 文件解密
     */
    public static void decryptFileAES(File in, File out, final String keyStr, final String iv) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, getKey(keyStr), getIv(iv));
            CipherInputStream is = new CipherInputStream(new FileInputStream(in), cipher);
            FileOutputStream  os = new FileOutputStream(out);
            writeFile(is, os);
        } catch (Exception e) {
            throw new RuntimeException("AES decryptFileAES error", e);
        }
    }

    /**
     * 写文件
     */
    private static void writeFile(InputStream is, OutputStream os) throws IOException {
        try {
            int    i;
            byte[] b = new byte[1024];
            while ((i = is.read(b)) != -1) {
                os.write(b, 0, i);
            }
        } finally {
            // 关闭流
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    LOGGER.error("AESUtil copy InputStream close erro", e);
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    LOGGER.error("AESUtil copy OutputStream close erro", e);
                }
            }
        }
    }

    /**
     * string转key对象
     */
    private static Key getKey(final String keyStr) {
        return new SecretKeySpec(Base64.decode(keyStr.getBytes()), "AES");
    }

    /**
     * string转IV对象
     */
    private static AlgorithmParameterSpec getIv(final String iv) {
        return new IvParameterSpec(Base64.decode(iv));
    }
}