package com.saga.shipment.application.controller.api.response;

public record DeliveredShipment(
        String packageId,
        boolean delivered
){
}
