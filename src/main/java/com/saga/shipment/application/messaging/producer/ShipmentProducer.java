package com.saga.shipment.application.messaging.producer;

import com.saga.shipment.application.api.event.CheckDeliveryProcessResponse;
import com.saga.shipment.application.api.event.ItemServicingProcessResponse;
import com.saga.shipment.domain.model.ItemServicingRequest;
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

    @Override
    public void packageIsDelivered(String packageId, boolean isDelivered, ItemServicingRequest request) {
        CheckDeliveryProcessResponse response = CheckDeliveryProcessResponse.builder()
                .response(new CheckDeliveryProcessResponse.DeliveredPackageResponse(packageId, isDelivered))
                .processId(request.processId())
                .businessKey(request.businessKey())
                .build();
        streamBridge.send(StreamBindingConstants.DELIVERED, MessageBuilder.withPayload(response).build());
    }

    @Override
    public void notifyOfDelivery(ItemServicingRequest request) {
        var response = new ItemServicingProcessResponse(request.processId(), request.businessKey(), null);
        streamBridge.send(StreamBindingConstants.NOTIFY_DELIVERY, MessageBuilder.withPayload(response).build());
    }
}
