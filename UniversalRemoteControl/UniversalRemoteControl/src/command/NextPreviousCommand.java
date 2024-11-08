package command;

import device.IDevice;

import java.util.ArrayList;
import java.util.List;

public class NextPreviousCommand implements ICommand {
    private IDevice device;
    private List<Boolean> previousStates;

    public NextPreviousCommand(IDevice device) {
        this.device = device;
        this.previousStates = new ArrayList<Boolean>();
    }

    public boolean execute() {
        if(device.isNext()) {
            device.previous();
            previousStates.add(true);
        } else {
            device.next();
            previousStates.add(false);
        }
        return device.isNext();
    }

    public boolean undo() {
        if(!this.previousStates.isEmpty()) {
            boolean state = this.previousStates.removeLast();
            if(state) {
                device.next();
            } else {
                device.previous();
            }
        }
        return device.isNext();

    }
}
