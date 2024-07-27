package com.saga.shipment.infra.repository.jpa;

import com.saga.shipment.infra.model.ClaimEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimEntityRepository extends JpaRepository<ClaimEntity, Integer> {
}
