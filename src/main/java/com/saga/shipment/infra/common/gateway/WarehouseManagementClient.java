package com.saga.shipment.infra.common.gateway;

import com.saga.shipment.infra.common.gateway.dto.request.PackageIdsRequest;
import com.saga.shipment.infra.common.gateway.dto.response.WarehouseItemResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        value = "wms-client",
        url = "${http.client.warehouse.url}"
)
public interface WarehouseManagementClient {

    @GetMapping
    ResponseEntity<List<WarehouseItemResponse>> getAllItems(@RequestBody PackageIdsRequest packageIds);

}
