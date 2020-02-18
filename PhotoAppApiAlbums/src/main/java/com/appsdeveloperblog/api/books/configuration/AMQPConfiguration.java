package com.appsdeveloperblog.api.books.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

import com.appsdeveloperblog.api.books.messages.PracticalTipListener;

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

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(amqpProperties.getQueueName());
		container.setMessageListener(listenerAdapter);
		return container;
	}

	/**
	 * @return This converter is used to be able to send and receive json messages
	 */
	@Bean
	public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}

	/**
	 * @param listener
	 * @return the method 'listen' is the name of the method which will be invoked
	 *         to listen to messages when messages are received
	 */
	@Bean
	MessageListenerAdapter listenerAdapter(PracticalTipListener listener) {
		return new MessageListenerAdapter(listener, "listen");
	}
}
