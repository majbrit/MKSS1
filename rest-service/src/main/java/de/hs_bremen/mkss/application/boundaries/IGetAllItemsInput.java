package de.hs_bremen.mkss.application.boundaries;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface IGetAllItemsInput {
    public void getAllItems(UUID id);
}
