package com.appsdeveloperblog.api.books.messages;

import org.springframework.stereotype.Component;

@Component
public class PracticalTipListener {

//	private static final Logger log = LoggerFactory.getLogger(PracticalTipListener.class);

	public void listen(byte[] message) {
		String msg = new String(message);
		System.out.println("Received a new notification...");
		System.out.println(msg);

		callActionWithReceivedMsg(msg);

	}

	private void callActionWithReceivedMsg(String msg) {
		System.out.println("Action Triggered = :" + msg );
	}
}
