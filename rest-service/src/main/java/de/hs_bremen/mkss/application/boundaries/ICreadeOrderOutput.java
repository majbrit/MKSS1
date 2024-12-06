package de.hs_bremen.mkss.application.boundaries;

import de.hs_bremen.mkss.domain.order.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface ICreadeOrderOutput {
    public void onCreateOrderResult(UUID orderId);
}
