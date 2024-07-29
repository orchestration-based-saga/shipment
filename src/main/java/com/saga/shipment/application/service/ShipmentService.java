package com.saga.shipment.application.service;

import com.saga.shipment.application.controller.api.response.DeliveredShipment;
import com.saga.shipment.application.mapper.ShipmentMapper;
import com.saga.shipment.domain.in.ShipmentServiceApi;
import com.saga.shipment.domain.model.DeliveredPackage;
import com.saga.shipment.domain.model.enums.PackageStatus;
import com.saga.shipment.domain.model.enums.ShipmentDomainStatus;
import com.saga.shipment.domain.out.WarehouseClientApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentServiceApi shipmentServiceApi;
    private final WarehouseClientApi client;
    private final ShipmentMapper shipmentMapper;

    public List<DeliveredShipment> checkForDeliveredShipments(List<String> packageIds) {
        List<DeliveredPackage> deliveredPackages = client.getPackages(packageIds)
                .stream()
                .filter(item -> item.status().equals(PackageStatus.DELIVERED))
                .toList();
        deliveredPackages.forEach(pack -> shipmentServiceApi.updateStatus(pack.packageId(), ShipmentDomainStatus.DELIVERED));
        return shipmentMapper.toResponse(deliveredPackages);
    }
}
