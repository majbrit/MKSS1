package remotecontrol;

import button.IActionButton;

/**
 * Interface for a generic remote control.
 */
public interface IRemoteControl {

    // TODO: Method for configuration of action buttons
    public void configureButton(int buttonNumber, IActionButton button);

    /**
     * The action button was pressed.
     * Depending on its status, it will execute an activate or deactivate action.
     *
     * @param buttonNumber The number of the button.
     */
    public void actionButtonPressed(int buttonNumber);

    /**
     * The undo button was pressed.
     * It will undo the previous action.
     */
    public void undoButtonPressed();
}
