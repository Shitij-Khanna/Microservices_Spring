package com.microservice.users.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PracticalTipMessage  {

	private final String text;
	private final int priority;
	private final boolean secret;

	public PracticalTipMessage(@JsonProperty("text") final String text, 
			@JsonProperty("priority") final int priority,
			@JsonProperty("secret") final boolean secret) {
		this.text = text;
		this.priority = priority;
		this.secret = secret;
	}

	public String getText() {
		return text;
	}

	public int getPriority() {
		return priority;
	}

	public boolean isSecret() {
		return secret;
	}

	@Override
	public String toString() {
		return "Practical message {" + "text = " + text + " and priority = " + priority + " and secret = " + secret
				+ "}";
	}
}
