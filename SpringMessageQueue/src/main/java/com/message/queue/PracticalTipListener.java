package com.message.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class PracticalTipListener {

	private static final Logger log = LoggerFactory.getLogger(PracticalTipListener.class);

	@RabbitListener(queues = SpringMessageQueueApplication.DEFAULT_PARSING_QUEUE)
	public void consumeDefaultMessage(final PracticalTipMessage message) {
		log.info("Received message with default configuration {}" + message.getText());
	}
}
