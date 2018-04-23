package com.example.shoppingmanager.utils;

import org.apache.log4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.UUID;

/**
 * 通用方法工具类
 *
 * @author liuzifeng
 */
public class CommonUtil {
    private static final String STRING_FORMAT = "appId:%s_requestId:%s_key:%s";

    /**
     * 日志工具
     */
    private static final Logger logger = Logger.getLogger(CommonUtil.class);

    public static String getMachineName() {
        String machineName = "";
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
            //获取本机ip
            String ip = addr.getHostAddress().toString();
            //获取本机计算机名称
            String hostName = addr.getHostName().toString();
            machineName += hostName + "," + ip;
        } catch (UnknownHostException e) {
            //do nothing
        }
        return machineName;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().toLowerCase();
    }

    /**
     * 生成标准的32位MD5值
     * @param text
     * @return
     */
    public static String md5(String text) {
        try {
            StringBuilder sb = new StringBuilder();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes(StandardCharsets.UTF_8));
            for (byte b : md.digest()) {
                int n = b;
                if(n < 0) {
                    n += 256;
                }
                if(n < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(n));
            }
            return sb.toString();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
