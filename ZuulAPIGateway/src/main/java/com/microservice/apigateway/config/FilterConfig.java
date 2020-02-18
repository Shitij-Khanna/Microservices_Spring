package com.microservice.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microservice.apigateway.filters.ZuulPostFilter_Simple;
import com.microservice.apigateway.filters.ZuulPreFilter_Simple;
import com.microservice.apigateway.filters.ZuulSecondPreFilter_Simple;

@Configuration
public class FilterConfig {

	
	@Bean
	public ZuulPreFilter_Simple simplePreFilter() {
		return new ZuulPreFilter_Simple();
	}
	
	@Bean
	public ZuulPostFilter_Simple simplePostFilter() {
		return new ZuulPostFilter_Simple();
	}
	
	@Bean
	public ZuulSecondPreFilter_Simple simplePreFilterSecond() {
		return new ZuulSecondPreFilter_Simple();
	}
}
