package history;

import command.ICommand;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {
    private List<ICommand> history;

    public CommandHistory() {
        this.history = new ArrayList<>();
    }

    /**
     * Save pressed button in a list, so it can be undone when needed
     * @param command The number of the button.
     */
    public void pushCommand(ICommand command) {
        this.history.add(command);
    }

    /**
     * Returns command of last pressed button and deletes it from the list
     * @return command The number of the button.
     */
    public ICommand popCommand() {
        if(this.history.size() == 0) {
            return null;
        }
        ICommand command = this.history.removeLast();
        return command;
    }
}
