package com.saga.shipment.application.messaging.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SuborderEvent(
        Integer id,
        Integer merchantId,
        Set<SuborderItemEvent> items
) {
}
