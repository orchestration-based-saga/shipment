package com.saga.shipment.infra.model;

import com.saga.shipment.infra.model.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "Shipment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Enumerated(EnumType.STRING)
    ShipmentStatus status;
    String orderId;
    UUID recipientId;
    UUID senderId;
    @OneToOne
    @JoinColumn(name = "claim_id", referencedColumnName = "id")
    ClaimEntity claim;
    Integer merchantInventoryId;
    Integer itemId;
    String packageId;
}
