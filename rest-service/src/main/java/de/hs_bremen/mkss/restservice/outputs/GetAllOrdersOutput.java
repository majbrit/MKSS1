package de.hs_bremen.mkss.restservice.outputs;

import de.hs_bremen.mkss.application.boundaries.IGetAllOrdersOutput;
import de.hs_bremen.mkss.domain.order.Order;

import java.util.List;

public class GetAllOrdersOutput implements IGetAllOrdersOutput {
    public List<Order> orders;

    @Override
    public void onGetAllOrdersResult(List<Order> orders) {
        this.orders = orders;
    }
}
