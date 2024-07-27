package com.saga.shipment.domain.model;

import com.saga.shipment.domain.model.enums.ClaimStatusDomain;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record Claim(
        Integer id,
        BigDecimal refundAmount,
        ClaimStatusDomain status
) {

    public Claim updateStatus(ClaimStatusDomain status) {
        return new Claim(id, refundAmount, status);
    }
}
