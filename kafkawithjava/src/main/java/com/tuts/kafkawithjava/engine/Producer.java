package com.tuts.kafkawithjava.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
  
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message,String topicType) {
        logger.info(String.format("Message produced by %s and message is %s",topicType, message));
        this.kafkaTemplate.send(topicType, message);
    }
}
