package com.saga.shipment.domain.model;

import java.util.Set;

public record Suborder(
        Integer id,
        Integer merchantId,
        Set<SuborderItem> items
) {
}
