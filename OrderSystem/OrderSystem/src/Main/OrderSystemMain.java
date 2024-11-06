package Main;

import Services.IItemFactory;
import Services.SimpleItemFactory;
import Ui.CUI;

public class OrderSystemMain {
    public static void main(String[] args) {
        CUI cui = new CUI();

        IItemFactory itemFactory = new SimpleItemFactory();
        cui.SetItemFactory(itemFactory);

        cui.menuloop();
    }
}