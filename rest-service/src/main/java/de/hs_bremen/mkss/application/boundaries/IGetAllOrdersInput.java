package de.hs_bremen.mkss.application.boundaries;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IGetAllOrdersInput {
    public List getAllOrders();
}
