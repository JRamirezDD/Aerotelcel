package com.subscription_redis.repository;

// Importing required classes
import com.subscription_redis.model.AviationDataSubscriptions;
import com.subscription_redis.model.Subscription;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Annotation
@Repository
public interface SubscriptionRepository extends CrudRepository<AviationDataSubscriptions, String> {
}