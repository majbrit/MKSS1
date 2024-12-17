package de.hs_bremen.mkss.application.boundaries;

import de.hs_bremen.mkss.domain.order.Order;

public interface IGetOrderByIdInput {
    Order getOrderById(Long orderId);
}
