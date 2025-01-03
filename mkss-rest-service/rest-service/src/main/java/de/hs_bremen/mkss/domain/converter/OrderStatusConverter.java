package de.hs_bremen.mkss.domain.converter;

import de.hs_bremen.mkss.domain.order.Order;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<Order.OrderStatus, String> {

    @Override
    public String convertToDatabaseColumn(Order.OrderStatus status) {
        if (status == null) {
            return null;
        }
        return status.name();
    }

    @Override
    public Order.OrderStatus convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        return Order.OrderStatus.valueOf(dbData);
    }
}

