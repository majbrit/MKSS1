package de.hs_bremen.mkss.application.boundaries;

import de.hs_bremen.mkss.domain.order.Order;

public interface IPurchaseOrderInput {
    Order purchaseOrder(Long orderId);
}
