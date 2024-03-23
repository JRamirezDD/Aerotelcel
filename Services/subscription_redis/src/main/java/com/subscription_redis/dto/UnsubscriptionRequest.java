/*
 *    Title: UnsubscriptionRequest Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.subscription_redis.dto;

public record UnsubscriptionRequest(String email, String aviationDataID) {
}
