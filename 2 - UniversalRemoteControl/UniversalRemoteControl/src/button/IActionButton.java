package button;

import command.ICommand;

public interface IActionButton {
    /**
     * The activation will be executed, e.g. on, play, next
     *
     */
    public void activate();

    /**
     * The deactivation will be executed, e.g. off, pause, previous
     *
     */
    public void deactivate();

    /**
     * Getter for activateCommand
     *
     * @return activateCommand Command to activate the action.
     */
    public ICommand getActivateCommand();

    /**
     * Getter for deactivateCommand
     *
     * @return deactivateCommand Command to deactivate the action.
     */
    public ICommand getDeactivateCommand();

}
