package de.hs_bremen.mkss.application.boundaries;

import de.hs_bremen.mkss.domain.item.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IGetAllItemsOutput {
    public void onGetAllItemsResult(List<Item> items);
}
