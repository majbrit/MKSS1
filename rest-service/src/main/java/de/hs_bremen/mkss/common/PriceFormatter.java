package de.hs_bremen.mkss.common;

public class PriceFormatter {
    public static String formatPrice(int priceInCent) {
        return String.format("%.2f EUR", priceInCent / 100.0);
    }
}
