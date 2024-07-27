package com.saga.shipment.domain.model;

import com.saga.shipment.domain.model.enums.ClaimStatusDomain;

import java.math.BigDecimal;

public record Claim(
        Integer id,
        BigDecimal refundAmount,
        ClaimStatusDomain status
) {
}
