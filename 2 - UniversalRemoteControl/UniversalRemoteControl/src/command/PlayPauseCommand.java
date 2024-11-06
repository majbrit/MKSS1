package command;

import device.IDevice;

import java.util.ArrayList;
import java.util.List;

public class PlayPauseCommand implements ICommand {
    private IDevice device;
    private List<Boolean> previousStates;

    public PlayPauseCommand(IDevice device) {
        this.device = device;
        previousStates = new ArrayList<Boolean>();
    }


    public boolean execute() {
        if(device.isPlay()) {
            device.pause();
            previousStates.add(true);
        } else {
            device.play();
            previousStates.add(false);
        }
        return device.isPlay();
    }

    public boolean undo() {
        if (!this.previousStates.isEmpty()) {
            boolean state = this.previousStates.removeLast();
            if (state) {
                device.next();
            } else {
                device.previous();
            }
        }
        return device.isPlay();
    }
}
