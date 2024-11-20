package application.boundaries;

import domain.item.Item;

import java.util.List;

public interface IGetAllItemsOutput {
    void onGetAllItemsResult(List<Item> items);
}
