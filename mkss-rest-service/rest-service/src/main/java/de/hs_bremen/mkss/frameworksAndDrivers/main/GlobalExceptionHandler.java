package de.hs_bremen.mkss.frameworksAndDrivers.main;

import de.hs_bremen.mkss.application.exceptions.EmptyOrderException;
import de.hs_bremen.mkss.application.exceptions.ItemNotFoundException;
import de.hs_bremen.mkss.application.exceptions.OrderAlreadyBoughtException;
import de.hs_bremen.mkss.application.exceptions.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleOrderNotFound(OrderNotFoundException ex) {

        return Map.of(
                "error", "Not Found",
                "details", ex.getMessage()
        );
    }

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleItemNotFound(ItemNotFoundException ex) {

        return Map.of(
                "error", "Item Not Found",
                "details", ex.getMessage()
        );
    }

    @ExceptionHandler(EmptyOrderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleEmptyOrder(EmptyOrderException ex) {

        return Map.of(
                "error", "Empty Order",
                "details", ex.getMessage()
        );
    }

    @ExceptionHandler(OrderAlreadyBoughtException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleOrderAlreadyBought(OrderAlreadyBoughtException ex) {

        return Map.of(
                "error", "Order Already Purchased",
                "details", ex.getMessage()
        );
    }

}
