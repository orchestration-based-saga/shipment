package com.saga.shipment.infra.mapper;

import com.saga.shipment.domain.model.DeliveredPackage;
import com.saga.shipment.infra.common.gateway.dto.response.WarehouseItemResponse;
import org.mapstruct.Mapper;

@Mapper
public interface WarehouseItemMapper {

    DeliveredPackage toDomain(WarehouseItemResponse response);
}
