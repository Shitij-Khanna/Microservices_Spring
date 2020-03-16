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
	Queue marketingQueue() {
		return new Queue(amqpProperties.getMarketingQueueName(), false);
	}

	@Bean
	Queue financeQueue() {
		return new Queue(amqpProperties.getFinanceQueueName(), false);
	}

	@Bean
	Queue ITQueue() {
		return new Queue(amqpProperties.getItQueueName(), false);
	}

	@Bean
	public Binding queueToExchangeBinding() {
		return BindingBuilder.bind(defaultParsingQueue()).to(tipsExchange()).with(amqpProperties.getRoutingKey());
	}
	
	@Bean
	Binding marketingBinding() {
		return BindingBuilder.bind(marketingQueue()).to(tipsExchange()).with(amqpProperties.getMarketingRoutingKey());
	}
	
	@Bean
	Binding financeBinding() {
		return BindingBuilder.bind(financeQueue()).to(tipsExchange()).with(amqpProperties.getFinanceRoutingKey());
	}
	
	@Bean
	Binding ITBinding() {
		return BindingBuilder.bind(ITQueue()).to(tipsExchange()).with(amqpProperties.getItRoutingKey());
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
