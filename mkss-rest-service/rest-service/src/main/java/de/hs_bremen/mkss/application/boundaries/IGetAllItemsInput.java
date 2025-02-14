package de.hs_bremen.mkss.application.boundaries;

import de.hs_bremen.mkss.domain.order.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IGetAllItemsInput {
    public List getAllItems(Order order);

    public void deleteItem(Long orderid, Long itemId);
}
