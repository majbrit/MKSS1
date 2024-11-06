package remotecontrol;

import command.ICommand;
import history.CommandHistory;

/**
 *  Base implementation for the remote control.
 */
public class RemoteControl implements IRemoteControl {

		public final static int NO_OF_ACTION_BUTTONS = 3;
		private ICommand[] buttons;
		private CommandHistory commandHistory;


		public RemoteControl() {
			buttons = new ICommand[NO_OF_ACTION_BUTTONS];
			commandHistory = new CommandHistory();
		}

		/**
		 * method for configuration of action buttons
		 *
		 * @param buttonNumber The number of the button.
		 * @param button The action button to configure.
		 */
		public void configureButton(int buttonNumber, ICommand button) {
			if (buttonNumber >= 0 && buttonNumber < NO_OF_ACTION_BUTTONS) {
				buttons[buttonNumber] = button;
			} else {
				System.out.println("Invalid button number: " + buttonNumber);
			}
		}

		/**
		 * The action button was pressed.
		 * Depending on its status, it will execute an activate or deactivate action.
		 *
		 * @param buttonNumber The number of the button.
		 */
		public void actionButtonPressed(int buttonNumber) {
			System.out.println("Action button " + buttonNumber + " pressed");
			if (buttonNumber >= 0 && buttonNumber < buttons.length) {
				ICommand pressedButton = buttons[buttonNumber];
				boolean activated = pressedButton.execute();
				commandHistory.pushCommand(pressedButton);
				if(activated) {
					System.out.println("(Button " + buttonNumber + " was activated) \n ");
				} else {
					System.out.println("(Button " + buttonNumber + " was deactivated) \n ");
				}
			} else {
				System.out.println("Button " + buttonNumber + " not found \n");
			}

		}

		/**
		 * The undo button was pressed.
		 * It will undo the previous action.
		 */
		public void undoButtonPressed() {
			// Execute undo action
			System.out.println("Undo button pressed");
			// TODO: Execute undo action

			ICommand undoButton = commandHistory.popCommand();
			if(undoButton != null) {
				boolean activated = undoButton.undo();
				if(activated) {
					System.out.println("(Button deactivation was undone (activated)) \n");

				} else {
					System.out.println("(Button activation was undone (deactivated)) \n");
				}
			} else {
				System.out.println("Nothing to undo \n");
			}
		}
}
