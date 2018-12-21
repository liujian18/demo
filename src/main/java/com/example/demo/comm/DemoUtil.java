package com.example.demo.comm;


import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;


public class DemoUtil {
    //十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};

    /**把inputString加密*/
    public static String md5(String inputStr){
        return encodeByMD5(inputStr);
    }

    /**
     * 验证输入的密码是否正确
     * @param password 真正的密码（加密后的真密码）
     * @param inputString 输入的字符串
     * @return 验证结果，boolean类型
     */
    public static boolean authenticatePassword(String password,String inputString){
        if(password.equals(encodeByMD5(inputString))){
            return true;
        }else{
            return false;
        }
    }

    /**对字符串进行MD5编码*/
    private static String encodeByMD5(String originString){
        if (StringUtils.isBlank(originString)) {
            return null;
        }
        try {
            byte[] b = originString.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(b);
            byte[] hash = md.digest();
            StringBuffer outStrBuf = new StringBuffer(32);
            for (int i = 0; i < hash.length; i++) {
                int v = hash[i] & 0xFF;
                if (v < 16) {
                    outStrBuf.append('0');
                }
                outStrBuf.append(Integer.toString(v, 16).toLowerCase());
            }
            return outStrBuf.toString();
        } catch (Exception e) {
            System.out.println("sign_utils签名错误"+e.toString());
        }
        return null;

    }

    /**
     * 轮换字节数组为十六进制字符串
     * @param b 字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b){
        StringBuffer resultSb = new StringBuffer();
        for(int i=0;i<b.length;i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    //将一个字节转化成十六进制形式的字符串
    private static String byteToHexString(byte b){
        int n = b;
        if(n<0)
            n=256+n;
        int d1 = n/16;
        int d2 = n%16;
        return hexDigits[d1] + hexDigits[d2];
    }

}
