package command;

import device.IDevice;

public class PlayCommand implements ICommand {

    private IDevice device;

    public PlayCommand(IDevice device) {
        this.device = device;
    }

    /**
     * execute command
     */
    @Override
    public void execute() {
        device.play();
    }
}
