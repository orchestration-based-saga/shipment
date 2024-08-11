package com.saga.shipment.domain.model;

import com.saga.shipment.domain.model.enums.ClaimStatusDomain;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record Claim(
        Integer id,
        String orderId,
        BigDecimal refundAmount,
        ClaimStatusDomain status,
        Integer merchantInventoryId,
        Integer itemId
) {

    public Claim updateStatus(ClaimStatusDomain status) {
        return new Claim(id, orderId, refundAmount, status, merchantInventoryId, itemId);
    }

    public Claim updateMerchantInventoryIdItemIdAndOrderId(Integer merchantInventoryId, Integer itemId, String orderId) {
        return new Claim(id, orderId, refundAmount, status, merchantInventoryId, itemId);
    }
}
