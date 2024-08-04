package com.saga.shipment.application.api.response;

public record DeliveredShipment(
        String packageId,
        boolean delivered
){
}
