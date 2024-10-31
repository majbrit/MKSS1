import button.ActionButton;
import button.IActionButton;
import command.*;
import device.Device1;
import device.IDevice;
import remotecontrol.*;

/**
 * Main for testing the functionality
 */
public class Main {
	public static void main(String[] args) {
		RemoteControl mediaPlayerRemote = new RemoteControl();



		// TODO: configure the buttons for the media player remote
		IDevice device1 = new Device1();
		IActionButton onOffButton = new ActionButton(new OnCommand(device1), new OffCommand(device1));
		IActionButton playPauseButton = new ActionButton(new PlayCommand(device1), new PauseCommand(device1));
		IActionButton nextPreviousButton = new ActionButton(new NextCommand(device1), new PreviousCommand(device1));
		mediaPlayerRemote.configureButton(0, onOffButton);
		mediaPlayerRemote.configureButton(1, playPauseButton);
		mediaPlayerRemote.configureButton(2, nextPreviousButton);

		// TODO: test the functionality by pressing different buttons similar to below
		mediaPlayerRemote.undoButtonPressed();
		mediaPlayerRemote.actionButtonPressed(0);
		mediaPlayerRemote.actionButtonPressed(1);
		mediaPlayerRemote.actionButtonPressed(2);
		mediaPlayerRemote.undoButtonPressed();
		mediaPlayerRemote.actionButtonPressed(0);
		mediaPlayerRemote.actionButtonPressed(1);
		mediaPlayerRemote.actionButtonPressed(2);

		mediaPlayerRemote.actionButtonPressed(1);
		mediaPlayerRemote.undoButtonPressed();

		mediaPlayerRemote.actionButtonPressed(0);
		mediaPlayerRemote.undoButtonPressed();
		mediaPlayerRemote.undoButtonPressed();
		mediaPlayerRemote.undoButtonPressed();
		mediaPlayerRemote.undoButtonPressed();
		mediaPlayerRemote.undoButtonPressed();
		mediaPlayerRemote.undoButtonPressed();
		mediaPlayerRemote.undoButtonPressed();
		mediaPlayerRemote.undoButtonPressed();
	}
}

