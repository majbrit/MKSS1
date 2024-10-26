package remotecontrol;

public class PlayCommand implements ICommand {

    private IDevice device;

    public PlayCommand(IDevice device) {
        this.device = device;
    }

    /**
     * execute command
     */
    public void execute() {
        device.play();
    }
}
