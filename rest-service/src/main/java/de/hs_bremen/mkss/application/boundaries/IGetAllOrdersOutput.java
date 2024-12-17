package de.hs_bremen.mkss.application.boundaries;

import de.hs_bremen.mkss.domain.order.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IGetAllOrdersOutput {
    public void onGetAllOrdersResult(List<Order> orders);
}