package com.test.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    /**
     * 使用MD5算法加密字符串
     *
     * @param input 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String input) {
        try {
            // 获取MD5摘要算法的 MessageDigest 对象
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 使用指定的字节更新摘要
            md.update(input.getBytes());

            // 完成哈希计算，得到加密后的字节数组
            byte[] digest = md.digest();

            // 将加密后的字节数组转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5加密算法在当前环境中不可用", e);
        }
    }

    public static void main(String[] args) {
        String originalPassword = "123456";
        String encryptedPassword = encrypt(originalPassword);
        System.out.println("原始密码: " + originalPassword);
        System.out.println("MD5加密后: " + encryptedPassword);
    }
}
