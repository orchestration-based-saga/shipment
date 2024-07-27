package com.saga.shipment.application.messaging.producer;

import com.saga.shipment.domain.model.Shipment;
import com.saga.shipment.domain.out.ShipmentProducerApi;
import com.saga.shipment.infra.common.event.StreamBindingConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShipmentProducer implements ShipmentProducerApi {

    private final StreamBridge streamBridge;

    public void sendShipment(Shipment shipment) {
        streamBridge.send(StreamBindingConstants.SHIPMENT, MessageBuilder.withPayload(shipment).build());
    }
}
