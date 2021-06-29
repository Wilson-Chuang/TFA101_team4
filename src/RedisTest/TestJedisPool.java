package RedisTest;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestJedisPool {
	private static JedisPool pool = null;

	public static void main(String[] args) {
		pool = JedisUtil.getJedisPool();
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		Set<String> keys = jedis.keys("*"); 
        Iterator<String> it=keys.iterator() ;   
        while(it.hasNext()){   
        	
            String key = it.next();   
            if(key.contains("1:測試5"))
            System.out.println(key); 
        }
		jedis.close();
		JedisUtil.shutdownJedisPool();
	}

}
