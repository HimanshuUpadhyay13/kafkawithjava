package com.tuts.kafkawithjava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

	@Query("select sub.email from Subscription sub inner join SubscriptionDetails details on sub.id=details.subscription and sub.topic=:topicName and details.objectName=:objectName")
	List<String> findDlForSubscribedObjects(@Param("topicName") String topicName,
			@Param("objectName") String objectName);

	@Query("select details.objectName, sub.email from Subscription sub inner join SubscriptionDetails details on sub.id=details.subscription and sub.topic=:topicName")
	List<String> findALlSubscribedObjetsByTopicName(String topicName);

}
