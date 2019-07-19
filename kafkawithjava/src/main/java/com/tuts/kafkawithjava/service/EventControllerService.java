package com.tuts.kafkawithjava.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tuts.kafkawithjava.repository.Subscription;
import com.tuts.kafkawithjava.repository.SubscriptionDetails;
import com.tuts.kafkawithjava.repository.SubscriptionRepository;
import com.tuts.kafkawithjava.response.SubscriptionResponse;

@Service
public class EventControllerService {

	@Resource
	private SubscriptionRepository subscriptionRepository;

	private static final Logger log = LoggerFactory.getLogger(EventControllerService.class);

	private static String REGEX = ",";

	/**
	 * @param name
	 * @param notificationDl
	 * @param topic
	 * @param objects
	 * @return subscription object saved to db
	 */
	public SubscriptionResponse subscribe(String name, String notificationDl, String topic, String objects) {
		Subscription subscription = new Subscription();
		subscription.setEmail(notificationDl);
		subscription.setName(name);
		subscription.setTopic(topic.toString());
		Set<SubscriptionDetails> details = new HashSet<SubscriptionDetails>();
		try {
			for (String object : objects.split(REGEX)) {
				SubscriptionDetails detail = new SubscriptionDetails();
				detail.setObjectName(object);
				details.add(detail);
			}
		} catch (Exception e) {
			log.info("Error while processing {}", e.getMessage());
		}
		subscription.getObjects().addAll(details);
		Subscription check = subscriptionRepository.save(subscription);
		SubscriptionResponse response = new SubscriptionResponse();
		response.setMessage("Subscription was successfull");
		response.setPipeline(check.getName());
		response.setSubscribedSystem(check.getTopic());
		response.setSubscribedObjects(Arrays.asList(objects.split(REGEX)));
		return response;
	}

}
