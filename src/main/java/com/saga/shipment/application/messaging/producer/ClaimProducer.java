package com.saga.shipment.application.messaging.producer;

import com.saga.shipment.application.api.enums.ClaimState;
import com.saga.shipment.application.api.event.ClaimUpdateMessage;
import com.saga.shipment.application.api.event.ItemServicingProcessResponse;
import com.saga.shipment.domain.model.Claim;
import com.saga.shipment.domain.model.ItemServicingRequest;
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

    public void sendShipmentId(Integer shipmentId, Claim claim, ItemServicingRequest request) {
        ClaimUpdateMessage claimUpdated = new ClaimUpdateMessage(
                claim.id(),
                shipmentId,
                ClaimState.valueOf(claim.status().name()));
        ItemServicingProcessResponse response = new ItemServicingProcessResponse(
                request.processId(),
                request.businessKey(),
                claimUpdated
        );
        streamBridge.send(StreamBindingConstants.UPDATE_CLAIM, MessageBuilder.withPayload(response).build()
        );
    }
}
