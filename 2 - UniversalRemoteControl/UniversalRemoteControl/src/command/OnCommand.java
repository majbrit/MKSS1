package command;

import device.IDevice;

public class OnCommand implements ICommand {

    private IDevice device;

    public OnCommand(IDevice device) {
        this.device = device;
    }

    /**
     * execute command
     */
    @Override
    public void execute() {
        device.on();
    }
}
