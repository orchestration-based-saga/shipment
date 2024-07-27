package com.saga.shipment.application.messaging.producer;

import com.saga.shipment.application.messaging.api.ClaimUpdate;
import com.saga.shipment.application.messaging.api.enums.ClaimState;
import com.saga.shipment.domain.model.Claim;
import com.saga.shipment.domain.model.Shipment;
import com.saga.shipment.domain.out.ClaimProducerApi;
import com.saga.shipment.infra.common.event.StreamBindingConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClaimProducer implements ClaimProducerApi {

    private final StreamBridge streamBridge;

    public void sendShipmentId(Integer shipmentId, Claim claim) {
        streamBridge.send(StreamBindingConstants.UPDATE_CLAIM, MessageBuilder.withPayload(
                        new ClaimUpdate(claim.id(), shipmentId, ClaimState.valueOf(claim.status().name()))
                ).build()
        );
    }
}
