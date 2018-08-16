package com.bridgeit.zuulapigateway.repository;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.bridgeit.zuulapigateway.security.TokenGenerator;

/**
 * <p>
 * <b>Implementation class for redis repository interface</b>
 * </p>
 * 
 * @author : Vishal Dhar Dubey
 * @version : 1.0
 * @since : 02-08-2018
 */
@Repository
public class RedisRepository implements IRedisRepository {

	private final static Logger LOGGER = LoggerFactory.getLogger(RedisRepository.class);

	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	TokenGenerator tokenGenerator;

	private HashOperations<String, String, String> hashOperation;

	/**
	 * <p>
	 * Constructor is to initialize the redistemplate
	 * </p>
	 * 
	 * @param redisTemplate
	 */
	public RedisRepository(RedisTemplate<String, Object> redisTemplate) {
		super();
		LOGGER.info("Insde the redisRepository Constructor");
		this.redisTemplate = redisTemplate;
	}

	/**
	 * <p>funtion is to initialize the redis tempate.</p>
	 */
	@PostConstruct
	private void init() {
		LOGGER.info("Insde the init function");
		hashOperation = redisTemplate.opsForHash();
	}

	/**
	 * <p>
	 * Function is to get the token from redis repository
	 * </p>
	 * 
	 * @param user id.
	 */
	@Override
	public String getToken(String userId) {
		LOGGER.info("Insde the getToken function");
		return hashOperation.get("TOKEN", userId);
	}

}
