package com.tuts.kafkawithjava.engine;

import java.io.IOException;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

	private static final Logger log = LoggerFactory.getLogger(Consumer.class);

	
	private final Logger logger = LoggerFactory.getLogger(Producer.class);

	/**
	 * 
	 * @param message
	 * @throws IOException
	 * @info Currently it is listening to one topic only
	 */
	@KafkaListener(topics = "topic_alpha", groupId = "group_id")
	public void consumeTeradataTopic(String message) throws IOException {
		logger.info(String.format("#### -> Consumed message -> %s", message));
		Executors.newSingleThreadExecutor().execute(() -> {
			log.info("notifying the subscribers for a subscribed change");
		});
	}

	@KafkaListener(topics = "topic_beta", groupId = "group_id")
	public void consumeScriptTopic(String message) throws IOException {
		logger.info(String.format("#### -> Consumed message -> %s", message));
		Executors.newSingleThreadExecutor().execute(() -> {
			log.info("notifying the subscribers for a subscribed change");
		});
	}
}
