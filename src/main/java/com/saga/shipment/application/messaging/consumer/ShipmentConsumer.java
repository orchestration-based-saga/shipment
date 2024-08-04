package com.saga.shipment.application.messaging.consumer;

import com.saga.shipment.application.api.event.CreateShipmentMessage;
import com.saga.shipment.application.mapper.ShipmentMapper;
import com.saga.shipment.application.api.event.OrderMessage;
import com.saga.shipment.application.api.event.UpdateShipmentStatusMessage;
import com.saga.shipment.domain.in.ShipmentServiceApi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class ShipmentConsumer {
    private final ShipmentServiceApi shipmentServiceApi;
    private final ShipmentMapper shipmentMapper;

    @Bean
    public Consumer<Message<CreateShipmentMessage>> createShipment() {
        return message -> {
            shipmentServiceApi.returnItemToWarehouse(shipmentMapper.fromMessage(message.getPayload()));
        };
    }

    @Bean
    public Consumer<Message<UpdateShipmentStatusMessage>> updateShipment() {
        return message -> {
            UpdateShipmentStatusMessage updateEvent = message.getPayload();
            shipmentServiceApi.updateStatus(List.of(updateEvent.packageId()), shipmentMapper.fromMessageStatus(updateEvent.status()));
        };
    }

    @Bean
    public Consumer<Message<OrderMessage>> order() {
        return message -> {
            shipmentServiceApi.processOrder(shipmentMapper.fromMessage(message.getPayload()));
        };
    }
}
