package command;

import device.IDevice;

import java.util.ArrayList;
import java.util.List;

public class OnOffCommand implements ICommand {
    private IDevice device;
    private List<Boolean> previousStates;

    public OnOffCommand(IDevice device) {
        this.device = device;
        previousStates = new ArrayList<>();
    }

    public boolean execute() {
        if(device.isOn()) {
            device.off();
            previousStates.add(true);
        } else {
            device.on();
            previousStates.add(false);
        }
        return device.isOn();
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
        return device.isOn();
    }
}
