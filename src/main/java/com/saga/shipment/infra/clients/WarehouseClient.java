package com.saga.shipment.infra.clients;

import com.saga.shipment.domain.model.DeliveredPackage;
import com.saga.shipment.domain.out.WarehouseClientApi;
import com.saga.shipment.infra.common.gateway.WarehouseManagementClient;
import com.saga.shipment.infra.common.gateway.dto.request.PackageIdsRequest;
import com.saga.shipment.infra.common.gateway.dto.response.WarehouseItemResponse;
import com.saga.shipment.infra.mapper.WarehouseItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WarehouseClient implements WarehouseClientApi {
    private final WarehouseManagementClient client;
    private final WarehouseItemMapper mapper;

    @Override
    public List<DeliveredPackage> getPackages(List<String> ids) {
        ResponseEntity<List<WarehouseItemResponse>> itemsResponse = client.getAllItems(new PackageIdsRequest(ids));
        if (itemsResponse.getStatusCode().isError() || itemsResponse.getBody() == null) {
            throw new RuntimeException("Couldn't check for delivered packages");
        }
        return  itemsResponse.getBody().stream().map(mapper::toDomain).toList();
    }
}
