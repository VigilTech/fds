package fdsys.cache

import com.zink.cache.{CacheFactory, Cache}

/**
  * Created by nigel on 02/12/2015.
  */
object CacheClient {

    val cache: Cache = CacheFactory.connect("192.168.99.100")
    cache.set("BBC1", "http://www.bbc.co.uk/iplayer/tv/bbc_one")
    System.out.println(cache.get("BBC1"))
    System.out.println(cache.get("BBC2"))
    cache.setnx("BBC1", "junk")
    System.out.println(cache.get("BBC1"))
    cache.del("BBC1")
    System.out.println(cache.get("BBC1"))
    cache.setnx("BBC1", "http://www.bbc.co.uk/iplayer/tv/bbc_one")
    cache.expire("BBC1", 100)
    System.out.println(cache.get("BBC1"))
    Thread.sleep(100)
    System.out.println(cache.get("BBC1"))


}
