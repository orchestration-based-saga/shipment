package com.saga.shipment.domain.model;

import com.saga.shipment.domain.model.enums.PackageStatus;

public record DeliveredPackage(
        String packageId,
        PackageStatus status
) {
}
