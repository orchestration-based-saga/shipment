package com.saga.shipment.infra.common.event;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamBindingConstants {
    public static final String UPDATE_CLAIM = "workflow-shipment-create-response";
    public static final String DELIVERED = "workflow-shipment-check-delivery-response";
    public static final String SHIPMENT = "shipment";
    public static final String NOTIFY_DELIVERY = "workflow-delivered-package-response";
}
