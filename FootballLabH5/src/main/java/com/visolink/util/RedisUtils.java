package com.visolink.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.poi.hssf.record.formula.functions.T;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {
	
	public static JedisPool jedisPool= null;
	public static synchronized Jedis getJedis()
	{
		if(jedisPool==null){
			GenericObjectPoolConfig poolConfig = new JedisPoolConfig();
			//设置最大空闲连接数
			poolConfig.setMaxIdle(10);
			//连接池中最大连接数
			poolConfig.setMaxTotal(100);
			//在获取链接的时候设置的超时时间
			poolConfig.setMaxWaitMillis(1000);
			//设置连接池在创建连接的时候，需要对新创建的连接进行测试，保证连接池中的连接都是可用的
			poolConfig.setTestOnBorrow(true);
//			jedisPool = new JedisPool(poolConfig, Const.REDIS_IP, Const.REDIS_PORT, 1000, Const.REDIS_PASSWORD);
			jedisPool = new JedisPool(poolConfig, "127.0.0.1", Const.REDIS_PORT, 1000);
		}
		return jedisPool.getResource();
	}
	
	/**
	 * 把连接放到连接池中
	 * @param jedis
	 */
	public static void returnJedis(Jedis jedis){
		jedisPool.returnResourceObject(jedis);
	}
	
	/**
	 * 添加数据 string：Object
	 * @param key
	 * @param obj
	 * @param db
	 */
	public static void add(String key,Object obj,long liveTime,int db){
		Jedis jedis = RedisUtils.getJedis();
		jedis.select(db);
		jedis.set(key, JsonUtil.beanToJson(obj));
		if (liveTime > 0) {
			jedis.expire(key, Integer.parseInt(String.valueOf(liveTime)));
		}
		RedisUtils.returnJedis(jedis);
	}
	
	/**
	 * 添加数据 string：string
	 * @param key
	 * @param value
	 * @param liveTime
	 * @param db
	 */
	public static void add(String key,String value,long liveTime,int db){
		Jedis jedis = RedisUtils.getJedis();
		jedis.select(db);
		jedis.set(key, value);
		if (liveTime > 0) {
			jedis.expire(key, Integer.parseInt(String.valueOf(liveTime)));
		}
		RedisUtils.returnJedis(jedis);
	}
	
	/**
	 * 获取value为string的值
	 * getString
	 * @param key
	 * @param db
	 * @return
	 */
	public static String getString(String key,int db){
		Jedis jedis = RedisUtils.getJedis();
		jedis.select(db);
		String data = jedis.get(key);
		RedisUtils.returnJedis(jedis);
		return data;
	}
	
	/**
	 * 
	 * @param keys
	 * @param db
	 * @param t
	 * @return
	 */
	public static <T> List<T> getList(String keys,int db,T t){
		Jedis jedis = RedisUtils.getJedis();
		jedis.select(db);
		Set<String> result = jedis.keys(keys);
		List<T> datas = new ArrayList<T>();
		for (String key : result) {
			String data = jedis.get(key);
			datas.add((T) JsonUtil.jsonToBean(data, t.getClass()));
		}
		return datas;
	}
	
	public static Map<String,Long> getMap(String keys,int db){
		
		Map<String,Long> result = new HashMap<String, Long>();
		
		Jedis jedis = RedisUtils.getJedis();
		jedis.select(db);
		Set<String> keySet = jedis.keys(keys);
		for (String key : keySet) {
			String value = jedis.get(key);
			result.put(key.replaceAll(keys.substring(0,keys.length()-1), ""), Long.parseLong(value));
		}
		return result;
	}
	
	public static void incr(String key,int db){
		Jedis jedis = RedisUtils.getJedis();
		jedis.select(db);
		jedis.incr(key);
		RedisUtils.returnJedis(jedis);
	}
	
	
}
