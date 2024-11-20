package application.boundaries;

import java.util.UUID;

public interface IGetOrderSummaryInput {
    public void getOrderSummary(UUID orderId);
}
