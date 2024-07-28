package com.saga.shipment.infra.common.gateway.dto.request;

import java.util.List;

public record PackageIdsRequest(
        List<String> packageIds
) {
}
