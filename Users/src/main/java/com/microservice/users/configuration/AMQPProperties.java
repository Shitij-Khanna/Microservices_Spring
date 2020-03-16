package com.microservice.users.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author nlshikha
 * @ConfigurationProperties would read the properties with prefix , and add the
 *                          field name to it, like rabbitmq.queueName, and the
 *                          property is set via the getter and setter methods
 */
@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
public class AMQPProperties {

	public String queueName;
	
	public String financeQueueName;
	
	public String  marketingQueueName;
	
	public String itQueueName;

	public String exchangeName;

	public String routingKey;
	
	public String financeRoutingKey;
	
	public String marketingRoutingKey;
	
	public String itRoutingKey;
	
	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getFinanceQueueName() {
		return financeQueueName;
	}

	public void setFinanceQueueName(String financeQueueName) {
		this.financeQueueName = financeQueueName;
	}

	public String getMarketingQueueName() {
		return marketingQueueName;
	}

	public void setMarketingQueueName(String marketingQueueName) {
		this.marketingQueueName = marketingQueueName;
	}

	public String getItQueueName() {
		return itQueueName;
	}

	public void setItQueueName(String itQueue) {
		this.itQueueName = itQueue;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}

	public String getFinanceRoutingKey() {
		return financeRoutingKey;
	}

	public void setFinanceRoutingKey(String financeRoutingKey) {
		this.financeRoutingKey = financeRoutingKey;
	}

	public String getMarketingRoutingKey() {
		return marketingRoutingKey;
	}

	public void setMarketingRoutingKey(String marketingRoutingKey) {
		this.marketingRoutingKey = marketingRoutingKey;
	}

	public String getItRoutingKey() {
		return itRoutingKey;
	}

	public void setItRoutingKey(String itRoutingKey) {
		this.itRoutingKey = itRoutingKey;
	}
}
