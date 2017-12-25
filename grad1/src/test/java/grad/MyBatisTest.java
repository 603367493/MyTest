package grad;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MyBatisTest {

	@Autowired
	public JedisPool jedisPool;
	
	
	@Test
	public void test(){
		System.out.println(jedisPool);
		set("age","16",1);
		System.out.println(get("age"));
	}
	
	private Jedis getJedis(){
		Jedis jedis = jedisPool.getResource();
		jedis.auth("shidi");
		return jedis;
	}
	
	
	public void set(String key ,String value ,int seconds){
		Jedis jedis = getJedis();
		jedis.setex(key, seconds, value);
	}
	
	public String get(String key){
		Jedis jedis = getJedis();
		return jedis.get(key);
	}
}
