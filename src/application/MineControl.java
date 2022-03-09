package application;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class MineControl implements EventHandler<MouseEvent> {
	MineField field;

	public MineControl(MineField field) {
		this.field = field;
	}

	@Override
	public void handle(MouseEvent event) {
		Button b = (Button) event.getSource();
		String[] coordinates = b.getId().split(" ");
		final int y = Integer.parseInt(coordinates[0]);
		final int x = Integer.parseInt(coordinates[1]);
		
		if (event.getButton() == MouseButton.SECONDARY) {
			field.play(x, y, MineField.ACTIONS.FLAG);
		}

		// Links
		if (event.getButton() == MouseButton.PRIMARY) {
			field.play(x, y, MineField.ACTIONS.CLICK);
		}
			
	}

}
