package com.saga.shipment.domain.out;

import com.saga.shipment.domain.model.DeliveredPackage;

import java.util.List;

public interface WarehouseClientApi {
    List<DeliveredPackage> getPackages(List<String> ids);
}
