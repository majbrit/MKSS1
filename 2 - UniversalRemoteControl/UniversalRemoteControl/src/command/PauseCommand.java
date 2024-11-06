package command;

import device.IDevice;

public class PauseCommand implements ICommand {

    private IDevice device;

    public PauseCommand(IDevice device) {
        this.device = device;
    }

    /**
     * execute command
     */
    @Override
    public void execute() {
        device.pause();
    }
}
