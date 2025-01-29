package de.hs_bremen.mkss.interfaceAdapters.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request object for creating a new order, containing customer details." )
public class CreateOrderRequest {
    @Schema(description = "Customer Name", example = "Elsa Hansen", required = true)
    private String customerName;
    public String getCustomerName() {
        return customerName;
    }
}
