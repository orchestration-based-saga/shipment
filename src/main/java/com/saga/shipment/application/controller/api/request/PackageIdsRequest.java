package com.saga.shipment.application.controller.api.request;

import java.util.List;

public record PackageIdsRequest(
        List<String> packageIds
) {
}

