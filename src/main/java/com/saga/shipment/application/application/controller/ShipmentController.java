package com.saga.shipment.application.application.controller;

import com.saga.shipment.application.application.api.request.PackageIdsRequest;
import com.saga.shipment.application.application.api.response.DeliveredShipment;
import com.saga.shipment.application.application.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipment")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    @PostMapping("/delivered-shipments")
    public ResponseEntity<List<DeliveredShipment>> deliveredShipments(@RequestBody PackageIdsRequest packageIds) {
        List<DeliveredShipment> deliveredShipments = shipmentService.checkForDeliveredShipments(packageIds.packageIds());
        return ResponseEntity.ok().body(deliveredShipments);
    }
}
