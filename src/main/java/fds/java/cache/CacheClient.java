package fds.java.cache;

/**
 * Created by nigel on 01/12/2015.
 */
import com.zink.cache.*;


public class CacheClient {

    public static void main(String[] args) throws Exception {

        Cache cache = CacheFactory.connect("192.168.99.100");

        cache.set("BBC1","http://www.bbc.co.uk/iplayer/tv/bbc_one");

        System.out.println(cache.get("BBC1"));
        System.out.println(cache.get("BBC2"));

        cache.setnx("BBC1","junk");
        System.out.println(cache.get("BBC1"));

        cache.del("BBC1");
        System.out.println(cache.get("BBC1"));

        cache.setnx("BBC1","http://www.bbc.co.uk/iplayer/tv/bbc_one");
        cache.expire("BBC1",100);
        System.out.println(cache.get("BBC1"));
        Thread.sleep(100);
        System.out.println(cache.get("BBC1"));
    }

}