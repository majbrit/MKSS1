package de.hs_bremen.mkss.application.boundaries;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface IAddProductInput {
    public void addProduct(UUID id, String name, int price, int quantity);
}
