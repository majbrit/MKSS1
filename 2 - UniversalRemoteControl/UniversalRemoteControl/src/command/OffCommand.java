package command;

import device.IDevice;

public class OffCommand implements ICommand {

    private IDevice device;

    public OffCommand(IDevice device) {
        this.device = device;
    }

    /**
     * execute command
     */
    @Override
    public void execute() {
        device.off();
    }
}
