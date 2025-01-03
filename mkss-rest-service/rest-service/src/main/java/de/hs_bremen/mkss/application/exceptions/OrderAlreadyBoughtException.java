package de.hs_bremen.mkss.application.exceptions;

public class OrderAlreadyBoughtException extends RuntimeException {
    public OrderAlreadyBoughtException(String message) {
        super(message);
    }
}