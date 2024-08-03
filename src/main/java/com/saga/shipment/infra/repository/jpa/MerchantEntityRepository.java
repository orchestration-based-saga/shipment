package com.saga.shipment.infra.repository.jpa;

import com.saga.shipment.infra.model.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantEntityRepository extends JpaRepository<MerchantEntity, Integer> {
}
