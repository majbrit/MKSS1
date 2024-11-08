package command;

import device.IDevice;

public interface ICommand {
    /**
     * execute command
     * @return activated
     */
    public boolean execute();

    /**
     * undo command
     * @return activated
     */
    public boolean undo();


}
