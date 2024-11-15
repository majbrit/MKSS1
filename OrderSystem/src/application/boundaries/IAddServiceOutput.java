package application.boundaries;

import domain.item.Service;

public interface IAddServiceOutput {
    public Service onAddServiceResult(Service service);
}
