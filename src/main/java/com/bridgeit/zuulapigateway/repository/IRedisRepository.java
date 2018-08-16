package com.bridgeit.zuulapigateway.repository;
/**
 * <p>
 * <b>Redis Interface for the Redis Repository</b>
 * </p>
 * 
 * @author : Vishal Dhar Dubey
 * @version : 1.0
 * @since : 02-08-2018
 */
public interface IRedisRepository {
	/**
	 * <p>
	 * Function is to get the token from redis repository of corresponding user Id
	 * </p>
	 * 
	 * @param user id.
	 */
	public String getToken(String userId);
}
