package button;

import command.ICommand;

public class ActionButton implements IActionButton {
    private ICommand activateCommand;
    private ICommand deactivateCommand;

    public ActionButton(ICommand activateCommand, ICommand deactivateCommand) {
        this.activateCommand = activateCommand;
        this.deactivateCommand = deactivateCommand;
    }

    /**
     * The activation will be executed, e.g. on, play, next
     *
     */
    public void activate() {
        activateCommand.execute();
    }

    /**
     * The deactivation will be executed, e.g. off, pause, previous
     *
     */
    public void deactivate() {
        deactivateCommand.execute();
    }

    /**
     * Getter for activateCommand
     *
     * @return activateCommand Command to activate the action.
     */
    public ICommand getActivateCommand() {
        return activateCommand;
    }

    /**
     * Getter for deactivateCommand
     *
     * @return deactivateCommand Command to deactivate the action.
     */
    public ICommand getDeactivateCommand() {
        return deactivateCommand;
    }
}
