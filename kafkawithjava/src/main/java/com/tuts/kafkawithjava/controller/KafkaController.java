package com.tuts.kafkawithjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuts.kafkawithjava.engine.Producer;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

	private final Producer producer;

	@Autowired
	KafkaController(Producer producer) {
		this.producer = producer;
	}

	/**
	 * 
	 * @param message
	 * @param topicType publish message to your topic
	 */
	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestBody String message, @RequestParam("topic") String topicType) {
		this.producer.sendMessage(message, topicType);
	}
}
