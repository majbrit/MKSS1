package de.hs_bremen.mkss.application.boundaries;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface IFinishOrderInput {
    public void finishOrder(UUID id);
}
