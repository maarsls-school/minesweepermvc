package application;

import java.awt.Container;
import java.awt.HeadlessException;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MineView extends Application implements Observer {
	MineField model;
	MineControl control;
	GridPane feld = new GridPane();
	Container pf = new Container();

	public MineView(MineField model, MineControl control) throws HeadlessException {
		this.model = model;
		this.control = control;
	}

	@Override
	public void update(Observable obs, Object message) {
		MineMessage msg = (MineMessage) message;
		Button b = (Button) (getNodeByRowColumnIndex(msg.height, msg.width, feld));
		if (msg.getAction().equals(MineMessage.ACTIONS.CELL_SET)) {
			b = (Button) (getNodeByRowColumnIndex(msg.height, msg.width, feld));
			b.setGraphic(null);
			b.setText(Integer.toString(msg.neighbourBombs));
			switch (msg.neighbourBombs) {
			case 0:
				b.setDisable(true);
				b.setText("");
				break;
			case 1:
				b.setStyle("-fx-background-color: #f4f4f8; ");
				break;
			case 2:
				b.setStyle("-fx-background-color: #fed766; ");
				break;
			case 3:
				b.setStyle("-fx-background-color: #2ab7ca; ");
				break;
			case 4:
				b.setStyle("-fx-background-color: #fe4a49; ");
				break;
			}
		} else if (msg.getAction().equals(MineMessage.ACTIONS.LOST)) {
			b.setText("");
			Image img = new Image(
					"https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/Bomb_icon.svg/437px-Bomb_icon.svg.png");
			ImageView view = new ImageView(img);
			view.setFitHeight(40);
			view.setPreserveRatio(true);
			b.setGraphic(view);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Minesweeper");
			alert.setHeaderText(null);
			alert.setContentText("Du hast verloren! Das Spiel wird neu gestartet");
			alert.showAndWait();
			((Stage) b.getScene().getWindow()).close();
			try {
				MineView.main(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (msg.getAction().equals(MineMessage.ACTIONS.FLAG)) {
			Image img = new Image(
					"https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/Flag_icon_red_4.svg/250px-Flag_icon_red_4.svg.png");
			ImageView view = new ImageView(img);
			view.setFitHeight(40);
			view.setPreserveRatio(true);
			b.setGraphic(view);
		} else if (msg.getAction().equals(MineMessage.ACTIONS.WON)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Minesweeper");
			alert.setHeaderText(null);
			alert.setContentText("Du hast gewonnen! Das Spiel wird neue gestartet");

			alert.showAndWait();
			((Stage) b.getScene().getWindow()).close();
			try {
				MineView.main(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
		Node result = null;
		ObservableList<Node> childrens = gridPane.getChildren();

		for (Node node : childrens) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
				result = node;
				break;
			}
		}

		return result;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		for (int i = 0; i < model.getHeight(); i++) {
			for (int j = 0; j < model.getWidth(); j++) {
				Button b = new Button();
				b.setStyle("-fx-background-radius: 0");
				b.setMaxHeight(80);
				b.setMaxWidth(80);
				b.setMinHeight(80);
				b.setMinWidth(80);

				b.addEventHandler(MouseEvent.MOUSE_CLICKED, control);
				b.setId(String.valueOf(i) + " " + String.valueOf(j));
				feld.add(b, j, i);
			}
		}
		BorderPane root = new BorderPane();
		root.setCenter(feld);

		model.addObserver(this);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public static void main(String[] args) throws Exception {
		MineField model = new MineField(10, 10, 20);
		model.toStringA();
		MineControl control = new MineControl(model);
		MineView view = new MineView(model, control);
		Platform.runLater(() -> {
			try {
				view.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}
