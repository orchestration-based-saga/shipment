package com.saga.shipment.application.messaging.consumer;

import com.saga.shipment.application.mapper.ShipmentMapper;
import com.saga.shipment.application.messaging.api.CreateShipment;
import com.saga.shipment.domain.in.ShipmentServiceApi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class ShipmentConsumer {
    private final ShipmentServiceApi shipmentServiceApi;
    private final ShipmentMapper shipmentMapper;

    @Bean
    public Consumer<Message<CreateShipment>> createShipment() {
        return message -> {
            shipmentServiceApi.returnItemToWarehouse(shipmentMapper.fromMessage(message.getPayload()));
        };
    }
}
