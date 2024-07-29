package com.saga.shipment.application.service;

import com.saga.shipment.application.controller.api.response.DeliveredShipment;
import com.saga.shipment.domain.in.ShipmentServiceApi;
import com.saga.shipment.domain.model.DeliveredPackage;
import com.saga.shipment.domain.model.enums.PackageStatus;
import com.saga.shipment.domain.model.enums.ShipmentDomainStatus;
import com.saga.shipment.domain.out.WarehouseClientApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentServiceApi shipmentServiceApi;
    private final WarehouseClientApi client;

    public List<DeliveredShipment> checkForDeliveredShipments(List<String> packageIds) {
        Map<PackageStatus, List<DeliveredPackage>> packages = client.getPackages(packageIds)
                .stream()
                .collect(
                        Collectors.groupingBy(pack -> pack.status().equals(PackageStatus.DELIVERED) ?
                                PackageStatus.DELIVERED : PackageStatus.IN_DELIVERY));
        List<String> deliveredPackages = packages.get(PackageStatus.DELIVERED)
                .stream().map(DeliveredPackage::packageId).toList();
        List<String> undeliveredPackages = packages.get(PackageStatus.IN_DELIVERY)
                .stream().map(DeliveredPackage::packageId).toList();
        shipmentServiceApi.updateStatus(deliveredPackages, ShipmentDomainStatus.DELIVERED);
        shipmentServiceApi.reassignCourier(undeliveredPackages);
        return deliveredPackages.stream().map(p -> new DeliveredShipment(p, true)).toList();
    }
}
