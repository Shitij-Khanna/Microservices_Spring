package com.appsdeveloperblog.api.books.configuration;

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

	public String exchangeName;

	public String routingKey;

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
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
}
