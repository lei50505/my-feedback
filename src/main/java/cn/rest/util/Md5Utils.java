package cn.rest.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class Md5Utils {
    private static MessageDigest md;
    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        
        System.out.println(strToStr("aaa"));
        System.out.println(strToStr3("aaa"));
    }

    public static byte[] bytesToBytes(byte[] bytes) {
        return md.digest(bytes);
    }
    
    public static byte[] strToBytes(String str){
        byte[] bytes = str.getBytes();
        return bytesToBytes(bytes);
    }
    
    public static String strToStr(String str){
        byte[] bytes = strToBytes(str);
        return Base64.encodeBase64String(bytes);
    }
    
    public static String strToStr3(String str){
        String encodeStr = strToStr(str);
        for(int i=0;i<2;i++){
            encodeStr = strToStr(encodeStr);
        }
        return encodeStr;
    }
}
