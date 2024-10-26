package command;

import device.IDevice;

public class PreviousCommand implements ICommand {

    private IDevice device;

    public PreviousCommand(IDevice device) {
        this.device = device;
    }

    /**
     * execute command
     */
    public void execute() {
        device.previous();
    }
}