package application.boundaries;

import java.util.UUID;

public interface IAddServiceInput {
    public void addService(UUID id, String name, int persons, int hours);
}
