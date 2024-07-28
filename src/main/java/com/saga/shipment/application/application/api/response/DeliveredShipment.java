package com.saga.shipment.application.application.api.response;

public record DeliveredShipment(
        String packageId,
        boolean delivered
){
}
