package com.saga.shipment.infra.config;

import com.saga.shipment.domain.in.ShipmentServiceApi;
import com.saga.shipment.domain.out.ClaimProducerApi;
import com.saga.shipment.domain.out.ShipmentRepositoryApi;
import com.saga.shipment.domain.service.ShipmentDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeanConfiguration {

    @Bean
    public ShipmentServiceApi shipmentServiceApi(
            ShipmentRepositoryApi shipmentRepositoryApi,
            ClaimProducerApi claimProducerApi) {
        return new ShipmentDomainService(shipmentRepositoryApi, claimProducerApi);
    }
}
