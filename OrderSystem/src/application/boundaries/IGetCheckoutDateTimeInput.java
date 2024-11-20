package application.boundaries;

import java.util.UUID;

public interface IGetCheckoutDateTimeInput {
    void getCheckoutDateTime(UUID orderId);
}
