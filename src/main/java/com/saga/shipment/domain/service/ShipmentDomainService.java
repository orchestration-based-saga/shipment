package com.saga.shipment.domain.service;

import com.saga.shipment.domain.in.ShipmentServiceApi;
import com.saga.shipment.domain.model.*;
import com.saga.shipment.domain.model.enums.ClaimStatusDomain;
import com.saga.shipment.domain.model.enums.OrderStatus;
import com.saga.shipment.domain.model.enums.PackageStatus;
import com.saga.shipment.domain.model.enums.ShipmentDomainStatus;
import com.saga.shipment.domain.out.ClaimProducerApi;
import com.saga.shipment.domain.out.ShipmentProducerApi;
import com.saga.shipment.domain.out.ShipmentRepositoryApi;
import com.saga.shipment.domain.out.WarehouseClientApi;
import com.saga.shipment.infra.model.enums.ShipmentStatus;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class ShipmentDomainService implements ShipmentServiceApi {
    private final ShipmentRepositoryApi shipmentRepositoryApi;
    private final ClaimProducerApi claimProducerApi;
    private final ShipmentProducerApi shipmentProducerApi;
    private final WarehouseClientApi warehouseClient;

    @Override
    public void returnItemToWarehouse(Shipment shipment, ItemServicingRequest request) {
        Integer shipmentId = shipmentRepositoryApi.createShipmentToWarehouse(shipment);
        Optional<Shipment> maybeShipment = shipmentRepositoryApi.findById(shipmentId);
        if (maybeShipment.isEmpty()) {
            throw new RuntimeException("Couldn't find shipment with id: " + shipmentId);
        }
        Shipment updatedShipment = maybeShipment.get();
        updatedShipment = updatedShipment.generatePackageId();
        updatedShipment.updateStatus(ShipmentDomainStatus.IN_DELIVERY);
        updatedShipment = shipmentRepositoryApi.save(updatedShipment);
        Claim updatedClaim = updatedShipment.claim().updateStatus(ClaimStatusDomain.IN_DELIVERY);
        updatedClaim = updatedClaim.updateMerchantInventoryIdItemIdAndOrderId(
                shipment.merchantInventoryId(),
                shipment.itemId(),
                shipment.orderId());
        claimProducerApi.updateClaimWithShipment(updatedShipment.packageId(), shipmentId, updatedClaim, request);
        shipmentProducerApi.sendShipment(updatedShipment);
    }

    @Override
    public void updateStatus(List<String> packageIds, ShipmentDomainStatus status) {
        for (String packageId : packageIds) {
            Optional<Shipment> maybeShipment = shipmentRepositoryApi.findByPackageId(packageId);
            if (maybeShipment.isEmpty()) {
                throw new RuntimeException("Couldn't find shipment with package id: " + packageId);
            }
            Shipment shipment = maybeShipment.get();
            shipment = shipment.updateStatus(status);
            shipmentRepositoryApi.save(shipment);
            shipmentProducerApi.sendShipment(shipment);
        }
    }

    @Override
    public void reassignCourier(List<String> packageIds) {
        List<Shipment> shipments = shipmentRepositoryApi.findByPackageIds(packageIds);
        shipments.forEach(shipmentProducerApi::sendShipment);
    }

    @Override
    public void processOrder(Order order) {
        if (!order.status().equals(OrderStatus.COMPLETED) && !order.status().equals(OrderStatus.PENDING)) {
            return;
        }
        for (Suborder suborder : order.suborders()) {
            UUID senderId = shipmentRepositoryApi.getUserIdOfMerchant(suborder.merchantId());
            String orderId = order.orderId();
            for (SuborderItem item : suborder.items()) {
                Shipment shipment = new Shipment(
                        null,
                        null,
                        orderId,
                        item.merchantInventoryId(),
                        item.id(),
                        null,
                        ShipmentDomainStatus.CREATED,
                        senderId,
                        order.customerId());
                shipment = shipment.generatePackageId();
                shipment = shipmentRepositoryApi.save(shipment);
                shipmentProducerApi.sendShipment(shipment);
            }
        }
    }

    @Override
    public void checkIfDelivered(String packageId, ItemServicingRequest request) {
        List<DeliveredPackage> deliveredPackages = warehouseClient.getPackages(List.of(packageId));
        deliveredPackages.forEach(deliveredPackage -> {
            if (deliveredPackage.status().equals(PackageStatus.DELIVERED)) {
                shipmentRepositoryApi.updateStatus(packageId, ShipmentStatus.DELIVERED);
                shipmentProducerApi.packageIsDelivered(packageId, true, request);
            } else {
                shipmentProducerApi.packageIsDelivered(packageId, false, request);
            }
        });

    }

    @Override
    public void notifyOfDelivery(String packageId, ItemServicingRequest request) {
        var shipment = shipmentRepositoryApi.findByPackageId(packageId);
        shipment.ifPresent(shipmentProducerApi::sendShipment);
        shipmentProducerApi.notifyOfDelivery(request);
    }
}
