package com.saga.shipment.application.api.event;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class CheckDeliveryProcessResponse{
    private final String businessKey;
    private final String processId;
    private final DeliveredPackageResponse response;


    @AllArgsConstructor
    @Getter
    @Setter
    public static class DeliveredPackageResponse{
        private String packageId;
        private Boolean isDelivered;
    }
}
