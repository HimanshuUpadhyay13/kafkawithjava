package com.tuts.kafkawithjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuts.kafkawithjava.engine.Producer;
import com.tuts.kafkawithjava.response.SubscriptionResponse;
import com.tuts.kafkawithjava.service.EventControllerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/events")
@Api(tags = "1) Event Subscription")
public class EventController {

	@Autowired
	private EventControllerService service;

	private final Producer producer;

	@Autowired
	EventController(Producer producer) {
		this.producer = producer;
	}

	@ApiOperation(value = "Subscription Endpoint", notes = "This endpoint allows user to subscribe to objects which they want to monitor for change", response = String.class, produces = "application/json")
	@PostMapping("/subscribe")
	public SubscriptionResponse subscribe(
			@ApiParam(value = "will create unique notification pipeline", required = true) @RequestParam("name") String name,
			@ApiParam(value = "will send notifications here", required = true) @RequestParam("notificationDl") String notificationDl,
			@ApiParam(value = "Choose a topic to Subscribe", required = true) @RequestParam("system") String system,
			@ApiParam(value = "provide object to be subscribed,pass multiple objects seprated by comma (,)", required = true) @RequestParam("systemObjects") String systemObjects) {
		return service.subscribe(name, notificationDl, system.toString(), systemObjects);
	}

	@ApiOperation(value = "Publish Endpoint", notes = "This is the endpoint where you will write data to kafka stream", response = String.class, produces = "application/json")
	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestBody String message, @RequestParam("topic") String topicType) {
		this.producer.sendMessage(message, topicType);
	}

}
