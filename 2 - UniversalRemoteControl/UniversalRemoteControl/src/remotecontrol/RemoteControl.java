package remotecontrol;

/**
 *  Base implementation for the remote control.
 */
public class RemoteControl implements IRemoteControl {

		public final static int NO_OF_ACTION_BUTTONS = 3;
		private boolean[] buttonStatus;
		private IActionButton[] buttons;
		private UndoButton undoButton;

		public RemoteControl() {
			buttonStatus = new boolean[NO_OF_ACTION_BUTTONS];
			buttons = new IActionButton[NO_OF_ACTION_BUTTONS];
			undoButton = new UndoButton();
		}

		/**
		 * method for configuration of action buttons
		 *
		 * @param buttonNumber The number of the button.
		 * @param button The action button to configure.
		 */
		public void configureButton(int buttonNumber, IActionButton button) {
			if (buttonNumber >= 0 && buttonNumber < NO_OF_ACTION_BUTTONS) {
				buttons[buttonNumber] = button;
				buttonStatus[buttonNumber] = false;
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
			IActionButton pressedButton = buttons[buttonNumber];
			if (pressedButton != null) {
				// Execute action
				if (!buttonStatus[buttonNumber]) {
					System.out.println("Button activated: " + buttonNumber);
					// TODO: Execute activation action
					pressedButton.activate();
					// TODO: Configure undo (deactivation) action
					undoButton.pushButtonNumber(buttonNumber);
				} else {
					System.out.println("Button deactivated: " + buttonNumber);
					// TODO: Execute deactivation action
					pressedButton.deactivate();
					// TODO: Configure undo (activation) action
					undoButton.pushButtonNumber(buttonNumber);
				}
				// Invert button status
				buttonStatus[buttonNumber] = !buttonStatus[buttonNumber];
			} else {
				System.out.println("Button not found: " + buttonNumber);
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
			Integer buttonNumber = undoButton.popButtonNumber();
			if (buttonNumber != null) {
				IActionButton button = buttons[buttonNumber];
				if (!buttonStatus[buttonNumber]) {
					System.out.println("Button deactivation undone (activated): " + buttonNumber);
					button.activate();
				} else {
					System.out.println("Button activation undone (deactivated): " + buttonNumber);
					button.deactivate();
				}
				// Invert button status
				buttonStatus[buttonNumber] = !buttonStatus[buttonNumber];
			} else {
				System.out.println("Nothing to undo");
			}
		}
}
