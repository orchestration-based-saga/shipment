package com.saga.shipment.infra.model;

import com.saga.shipment.infra.model.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.*;

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
    Integer recipientAddress;
    Integer senderAddress;
}
