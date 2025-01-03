package de.hs_bremen.mkss.application.boundaries;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface IGetOrderSummaryInput {
    public List getOrderSummary(Long orderId);
}
