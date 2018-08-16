package com.bridgeit.zuulapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.bridgeit.zuulapigateway.zuulapifilters.PreFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulserverApplication.class, args);
	}
	
	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
}
