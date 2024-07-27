package com.saga.shipment.infra.model;

import com.saga.shipment.infra.model.enums.ClaimStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "Claim")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClaimEntity {

    @Id
    Integer id;
    BigDecimal refundAmount;
    @Enumerated(EnumType.STRING)
    ClaimStatus status;
}
