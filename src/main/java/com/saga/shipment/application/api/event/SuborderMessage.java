package com.saga.shipment.application.api.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SuborderMessage(
        Integer id,
        Integer merchantId,
        Set<SuborderItemMessage> items
) {
}
