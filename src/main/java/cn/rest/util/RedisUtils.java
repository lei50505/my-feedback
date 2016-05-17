package cn.rest.util;

import redis.clients.jedis.Jedis;

public class RedisUtils {
    private static Jedis jedis;
    static {
        jedis = new Jedis(ConfigUtils.getString("redis.host"),
                ConfigUtils.getInt("redis.port"));
        jedis.auth(ConfigUtils.getString("redis.auth"));
    }

    public static void setStr(String key, String value) {
        jedis.set(key, value);
    }

    public static String getStr(String key) {
        return jedis.get(key);
    }
    public static void setInt(String key, int value) {
        setStr(key,String.valueOf(value));
    }
    
    public static Integer getInt(String key) {
        String str = getStr(key);
        if(str==null){
            return null;
        }
        return Integer.valueOf(str);
    }

    public static void expire(String key, int seconds) {
        jedis.expire(key, seconds);
    }

    public static void setStrWithExprSs(String key, String value, int seconds) {
        setStr(key, value);
        expire(key, seconds);
    }
    public static void setIntWithExprSs(String key, int value, int seconds) {
        setInt(key, value);
        expire(key, seconds);
    }
    
    public static void del(String key){
        jedis.del(key);
    }

}
