package com.tuts.kafkawithjava.repository;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity @Table(name = "subscriptions") @Data public class Subscription {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @NotNull @Size(max = 100) private String topic;

    @NotNull private String email;

    @NotNull private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscription_id", referencedColumnName = "id")
    private Set<SubscriptionDetails> objects = new HashSet<>();



}
