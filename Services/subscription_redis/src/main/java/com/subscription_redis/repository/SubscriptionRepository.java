package com.subscription_redis.repository;

// Importing required classes
import com.subscription_redis.model.AviationDataSubscriptions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Annotation
@Repository
public interface SubscriptionRepository extends CrudRepository<AviationDataSubscriptions, String> {
}