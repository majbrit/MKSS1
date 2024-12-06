package de.hs_bremen.mkss.application.boundaries;

import org.springframework.stereotype.Component;

@Component
public interface IClearOrdersOutput {
    public void onClearOrdersResult(boolean success);
}
