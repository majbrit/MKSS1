package command;

import device.IDevice;

public class NextCommand implements ICommand {

    private IDevice device;

    public NextCommand(IDevice device) {
        this.device = device;
    }

    /**
     * execute command
     */
    public void execute() {
        device.next();
    }
}
