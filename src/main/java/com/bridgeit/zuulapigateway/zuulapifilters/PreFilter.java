package com.bridgeit.zuulapigateway.zuulapifilters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bridgeit.zuulapigateway.exceptions.ToDoException;
import com.bridgeit.zuulapigateway.repository.IRedisRepository;
import com.bridgeit.zuulapigateway.security.TokenGenerator;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * <p>
 * <b>Pre Filter used for the varification of the user.</b>
 * </p>
 * 
 * @author : Vishal Dhar Dubey
 * @version : 1.0
 * @since : 09-07-2018
 */
@Component
public class PreFilter extends ZuulFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(PreFilter.class);

	@Autowired
	TokenGenerator tokenGenerator;

	@Autowired
	IRedisRepository iRedisRepository;

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	/*****************************************************************************************************************************
	 * <p>
	 * Function run is to varify the token and set it into request header
	 * </p>
	 * 
	 * @return reference
	 * @throws ToDoException
	 */
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		if (request.getRequestURI().startsWith("/note/")) {
			LOGGER.info("Inside the prehandle interceptor : " + request.getRequestURI());
			String token = request.getHeader("token");
			if(token==null) {
				return new ToDoException("NullPointerException : token can not be null");
			}
			System.out.println("token : " + token);
			String userId = tokenGenerator.parseJWT(token);
			System.out.println("user Id :" + userId);
			LOGGER.info(token);
			String tokenn = iRedisRepository.getToken(userId);
			System.out.println(tokenn);
			if (tokenn == null) {
				LOGGER.info("Ending the prehandle interceptor by returning :" + false);
				return false;
			}
			ctx.addZuulRequestHeader("userId", userId);
			LOGGER.info(tokenn);
			LOGGER.info("Ending the prehandle interceptor : " + request.getRequestURI());
		}
		return null;
	}
}
