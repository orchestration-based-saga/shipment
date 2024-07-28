package com.saga.shipment.application.application.api.request;

import java.util.List;

public record PackageIdsRequest(
        List<String> packageIds
) {
}

