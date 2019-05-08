package com.example.springbootshiro.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.Random;

/**
 * 加密的工具类
 */
public class EncryptUtils {
    /**
     * MD5加密
     * @param salt盐值为随机生成
     * @param source加密字符串
     * @return 加密后的结果
     */
    public static String encryptMD5(String salt,String source){
        return encryptByHashAlgorithmName("MD5", salt,source);
    }

    /**
     * SHA1加密
     * @param salt盐值为随机生成
     * @param source加密字符串
     * @return 加密后的结果
     */
    public static String encryptSHA1(String salt,String source){
        return encryptByHashAlgorithmName("SHA1",salt,source);
    }

    private static String encryptByHashAlgorithmName(String hashAlgorithmName,String salt,String source){
        String password = source;
        int hashIterations = 1024;

        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        Object obj = new SimpleHash(hashAlgorithmName, password, credentialsSalt, hashIterations);
        return obj.toString();
    }

    /**
     * 创建盐值
     * @param length盐值位数
     * @return 字母和数字的组合字符串
     */
    public static String createSalt(int length){
        String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";//保存数字0-9 和 大小写字母

        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            stringBuffer.append(str.charAt(number));
        }
        return stringBuffer.toString();
    }

    /**
     * 获取随机6位盐值
     * @return
     */
    public static String getSalt(){
        return createSalt(6);
    }

    public static void main(String[] args) {
        String salt = getSalt();
        System.out.println(salt);
//        String salt = "1gvEVM";
        String s = encryptMD5(salt, "123");
        String str = encryptSHA1(salt, "123");
        System.out.println("MD5: " + s);
        System.out.println("SHA1: " + str);
    }
}
