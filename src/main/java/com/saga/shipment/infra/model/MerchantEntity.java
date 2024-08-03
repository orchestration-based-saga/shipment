package com.saga.shipment.infra.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "Merchant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MerchantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    UUID userId;
    String tin;
    String name;
    @OneToOne
    @JoinColumn(name = "pickup_address", referencedColumnName = "id")
    MerchantAddressEntity pickupAddress;
    @OneToOne
    @JoinColumn(name = "return_address", referencedColumnName = "id")
    MerchantAddressEntity returnAddress;
}