package newimpl.control;


import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import newimpl.model.command.CommandRecorder;

public class UndoRedoControl implements EventHandler<MouseEvent> {

	private CommandRecorder recorder;

	public UndoRedoControl() {
		this.recorder = CommandRecorder.instance();
	}

	@Override
	public void handle(MouseEvent ev) {
		System.out.println("asködjfölasdf");
		if (((Node) ev.getSource()).getId().equals("UNDO")) {
			System.out.println("UNDO");
			recorder.undo();
		} else {
			System.out.println("REDO");
			recorder.redo();
		}

	}

}
