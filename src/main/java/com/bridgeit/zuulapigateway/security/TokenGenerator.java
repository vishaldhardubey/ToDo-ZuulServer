package com.bridgeit.zuulapigateway.security;

import java.sql.Date;

import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * <p>
 * <b>To generate the token.</b>
 * </p>
 * 
 * @author : Vishal Dhar Dubey
 * @version : 1.0
 * @since : 12-07-2018
 */
@Service
public class TokenGenerator {

	final static String KEY = "Vishal";

	/**
	 * <p>
	 * Function is to claim the token.
	 * </p>
	 * 
	 * @param jwt
	 * @return email ID
	 * @throws ToDoException
	 */
	public String parseJWT(String jwt) {

		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(KEY)).parseClaimsJws(jwt)
				.getBody();
		System.out.println("Subject: " + claims.getSubject());
		return claims.getSubject();
	}
}
