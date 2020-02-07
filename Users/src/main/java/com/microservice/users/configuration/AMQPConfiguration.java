package com.microservice.users.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfiguration {

	@Autowired
	private AMQPProperties amqpProperties;
	
	@Bean
	public TopicExchange tipsExchange() {
		return new TopicExchange(amqpProperties.getExchangeName());
	}

	@Bean
	public Queue defaultParsingQueue() {
		return new Queue(amqpProperties.getQueueName());
	}

	@Bean
	public Binding queueToExchangeBinding() {
		return BindingBuilder.bind(defaultParsingQueue()).to(tipsExchange()).with(amqpProperties.getRoutingKey());
	}

	@Bean
	public Jackson2JsonMessageConverter producerMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerMessageConverter());
		return rabbitTemplate;
	}
}
