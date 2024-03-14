//package com.subscription_handler.service;
//
//import com.subscription_handler.Service.FeignClients.Client_SubscriptionRedis;
//import com.subscription_handler.Service.SubscriptionHandlerService;
//import com.subscription_redis.dto.SubscriptionRequest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//class SubscriptionHandlerServiceTest {
//    @Mock
//    private Client_SubscriptionRedis subscriptionRedisClient;
//
//    @InjectMocks
//    private SubscriptionHandlerService subscriptionHandlerService;
//
//    @Test
//    public void whenSubscribe_thenDelegateToFeignClient() {
//        // Call the subscribe method without an exact instance of the SubscriptionRequest Object.
//        subscriptionHandlerService.subscribe(any(SubscriptionRequest.class));
//
//        // Verify that the Feign client's subscribe method was called with the same request object
//        verify(subscriptionRedisClient).subscribe(any(SubscriptionRequest.class));
//    }
//}
