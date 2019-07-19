package com.tuts.kafkawithjava.repository;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity @Table(name = "subscription_details") @Data public class SubscriptionDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @Column(name = "subscription_id") private Long subscription;

    @NotNull private String objectName;

}
