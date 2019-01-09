package com.bwie.wang.xiaomaipu.my.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * date:2018/12/29.
 *
 * @author 王丙均
 */

public class EncryptUtil {

    private static final String KEY = "12baweiyidong345";

    private static final String IV = "67baweiyidong899";

    /**
     * 加密
     *
     * @param passWord 要加密的数据
     *
     * @return 加密后的结果
     */
    public static String encrypt(String passWord) {
        try {
            //两个参数，第一个为私钥字节数组， 第二个为加密方式 AES或者DES
            Key             keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
            IvParameterSpec ivSpec  = new IvParameterSpec(IV.getBytes());
            //实例化加密类，参数为加密方式，要写全
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] b = cipher.doFinal(passWord.getBytes());
            return Base64.encode(b);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密
     *
     * @param password 要解密数据
     *
     * @return 解密后的数据
     */
    public static String decrypt(String password) {
        try {
            byte[]          byte1  = Base64.decode(password);
            IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes());
            Key key    = new SecretKeySpec(KEY.getBytes(), "AES");
            Cipher          cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            byte[] ret = cipher.doFinal(byte1);
            return new String(ret, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
