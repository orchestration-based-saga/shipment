package com.saga.shipment.application.api.request;

import java.util.List;

public record PackageIdsRequest(
        List<String> packageIds
) {
}

