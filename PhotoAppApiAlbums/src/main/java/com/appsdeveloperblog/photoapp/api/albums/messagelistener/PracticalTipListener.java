package com.appsdeveloperblog.photoapp.api.albums.messagelistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.photoapp.api.albums.configuration.AMQPConfiguration;
import com.appsdeveloperblog.photoapp.api.albums.configuration.AMQPProperties;

@Service
public class PracticalTipListener {

	private static final Logger log = LoggerFactory.getLogger(PracticalTipListener.class);

	 @RabbitListener(queues="${rabbitmq.queueName}")
	public void consumeDefaultMessage(final PracticalTipMessage message) {
		log.info("Received message with default configuration {}" + message.getText());
	}
	 
//	 public void listen(byte[] message) {
//	        String msg = new String(message);
//	        PracticalTipMessage not = new Gson().fromJson(msg, PracticalTipMessage.class);
//	        System.out.println("Received a new notification...");
//	        System.out.println(not.toString());
//	    }
}
