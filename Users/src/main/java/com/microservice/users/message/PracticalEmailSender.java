package com.microservice.users.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.microservice.users.configuration.AMQPProperties;

@Service
public class PracticalEmailSender {

	@Autowired
	private AMQPProperties properties;

	private static final Logger log = LoggerFactory.getLogger(PracticalEmailSender.class);

	private final RabbitTemplate rabbitTemplate;

	public PracticalEmailSender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Scheduled(fixedDelay = 3000L)
	public void sendPracticalTip() {
		PracticalTipMessage emailMessage = new PracticalTipMessage("Always use immutable classes in java", 1, false);
		rabbitTemplate.convertAndSend(properties.getExchangeName(), properties.getRoutingKey(), emailMessage);
		log.info("Practical email sent");
	}

}
