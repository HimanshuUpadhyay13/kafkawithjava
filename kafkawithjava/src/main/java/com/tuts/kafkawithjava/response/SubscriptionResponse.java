package com.tuts.kafkawithjava.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SubscriptionResponse {

	private String message;
	private String pipeline;
	private String subscribedSystem;
	private List<String> subscribedObjects = new ArrayList<String>();
}
