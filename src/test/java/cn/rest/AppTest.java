package cn.rest;

import java.util.Timer;
import java.util.TimerTask;

import redis.clients.jedis.Jedis;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost",6379);
        jedis.set("aaa", "123");
        System.out.println(jedis.get("aaa"));
        jedis.close();
    }
}
