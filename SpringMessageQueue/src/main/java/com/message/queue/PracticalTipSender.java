package com.message.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PracticalTipSender {

	private static final Logger log = LoggerFactory.getLogger(PracticalTipSender.class);

	private final RabbitTemplate rabbitTemplate;

	public PracticalTipSender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Scheduled(fixedDelay = 3000L)
	public void sendPracticalTip() {
		PracticalTipMessage tip = new PracticalTipMessage("Always use immutable classes in java", 1, false);
		rabbitTemplate.convertAndSend(SpringMessageQueueApplication.EXCHANGE_NAME,
				SpringMessageQueueApplication.ROUTING_KEY, tip);
		log.info("Practical tip sent");
	}

}
