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
		
		PracticalTipMessage financeMessage = new PracticalTipMessage("This is a message for finance queue", 1, false);
		rabbitTemplate.convertAndSend(properties.getExchangeName(), properties.getFinanceRoutingKey(), financeMessage);
		log.info("Finance message sent");
		
		PracticalTipMessage marketingMessage = new PracticalTipMessage("This is a message for marketing queue", 1, false);
		rabbitTemplate.convertAndSend(properties.getExchangeName(), properties.getMarketingRoutingKey(), marketingMessage);
		log.info("Marketing message sent");

		
		PracticalTipMessage itMessage = new PracticalTipMessage("This is a message for IT queue", 1, false);
		rabbitTemplate.convertAndSend(properties.getExchangeName(), properties.getItRoutingKey(), itMessage);
		log.info("IT message sent");

	}

}
