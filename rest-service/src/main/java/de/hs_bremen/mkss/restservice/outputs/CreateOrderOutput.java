package de.hs_bremen.mkss.restservice.outputs;

import de.hs_bremen.mkss.application.boundaries.ICreateOrderOutput;
import de.hs_bremen.mkss.domain.order.Order;

public class CreateOrderOutput implements ICreateOrderOutput {
    public Order order;
    @Override
    public void onCreateOrderResult(Order order) {
        this.order = order;
    }
}
