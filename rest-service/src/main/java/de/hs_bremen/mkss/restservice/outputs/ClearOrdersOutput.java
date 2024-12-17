package de.hs_bremen.mkss.restservice.outputs;

import de.hs_bremen.mkss.application.boundaries.IClearOrdersOutput;

public class ClearOrdersOutput implements IClearOrdersOutput {
    public boolean success;

    @Override
    public void onClearOrdersResult(boolean success) {
        this.success = success;
    }
}
