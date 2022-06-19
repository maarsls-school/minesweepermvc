package newimpl.control;


import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import newimpl.model.Playground;
import newimpl.model.command.CommandRecorder;
import newimpl.model.command.FlagCommand;

/**
 * Handle user clicks here and call the model
 */
public class MinesweeperControl implements EventHandler<MouseEvent> {
	private Playground field;
	private CommandRecorder recorder;
	
	public MinesweeperControl(Playground field) {
		this.field = field;
		this.recorder = CommandRecorder.instance();
	}

	@Override
	public void handle(MouseEvent event) {
		Button b = (Button) event.getSource();
		String[] coordinates = b.getId().split(" ");
		final int y = Integer.parseInt(coordinates[0]);
		final int x = Integer.parseInt(coordinates[1]);

		if (event.getButton() == MouseButton.PRIMARY) {
			FlagCommand c = new FlagCommand(field, x, y, 1);
			recorder.doIt(c);
//			field.play(x, y, Playground.ACTIONS.CLICK);
		} else if (event.getButton() == MouseButton.SECONDARY) {
			//field.play(x, y, Playground.ACTIONS.FLAG);
			FlagCommand c = new FlagCommand(field, x, y, 0);
			recorder.doIt(c);
		}

	}
}
