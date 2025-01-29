package de.hs_bremen.mkss.interfaceAdapters.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Entity representing an item within an order, including pricing and quantity details." )
public class OrderItem {
    @Schema(description = "Name or description of the item in the order", example = "BB", required = true)
    public String name;
    @Schema(description = "Price per unit of the item in the order", example = "3", required = true)
    public int price;
    @Schema(description = "Quantity of the item included in the order", example = "6", required = true)
    public int quantity;
}
