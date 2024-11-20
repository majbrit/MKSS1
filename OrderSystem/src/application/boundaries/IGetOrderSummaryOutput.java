package application.boundaries;

import domain.item.Item;

import java.util.List;

public interface IGetOrderSummaryOutput {
    public  void onGetOrderSummaryResult(List<Item> items, String sumString, String checkOut);
}
