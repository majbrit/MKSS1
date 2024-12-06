package de.hs_bremen.mkss.application.boundaries;

import org.springframework.stereotype.Component;

@Component
public interface IAddProductOutput {
    public void onAddProductResult(boolean success);
}
