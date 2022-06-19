package newimpl.view;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import newimpl.control.MinesweeperControl;
import newimpl.control.UndoRedoControl;
import newimpl.model.MinesweeperMessage;
import newimpl.model.Playground;
import newimpl.model.factory.FieldSerializer;
import newimpl.model.strategy.NormalStrategy;

public class MinesweeperView extends Application implements Observer {
	private Playground model;
	private MinesweeperControl control;
	private GridPane feld = new GridPane();

	private String IMG_HIDDEN = "images/circle_transparent.png";
	private String IMG_FLAG = "images/flag.png";
	private String IMG_BOMB = "images/bomb.png";

	public MinesweeperView(Playground model, MinesweeperControl control) {
		this.model = model;
		this.control = control;
	}

	private void setImg(Button b, String imgStr) {
		Image img = new Image(imgStr);
		ImageView view = new ImageView(img);
		view.setFitHeight(40);
		view.setPreserveRatio(true);
		b.setGraphic(view);
	}

	private void restart() {
		model.init();
		feld.getChildren().clear();
		fillGrid();
	}

	private void fillGrid() {
		for (int i = 0; i < model.getHeight(); i++) {
			for (int j = 0; j < model.getWidth(); j++) {
				Button b = new Button();
				b.setStyle("-fx-background-radius: 10; -fx-background-color: #E3E3E3;");
				b.setMaxHeight(80);
				b.setMaxWidth(80);
				b.setMinHeight(80);
				b.setMinWidth(80);
				setImg(b, IMG_HIDDEN);
				b.addEventHandler(MouseEvent.MOUSE_CLICKED, control);
				b.setId(String.valueOf(i) + " " + String.valueOf(j));
				feld.add(b, j, i);
			}
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		UndoRedoControl controlur = new UndoRedoControl();
//		MenuBar menuBar = new MenuBar();
//		Menu menu1 = new Menu("Aktionen");
//		MenuItem menuItem1 = new MenuItem("Undo");
//		menuItem1.setId("UNDO");
//		menuItem1.addEventHandler(MouseEvent.MOUSE_CLICKED, controlur);
//		MenuItem menuItem2 = new MenuItem("Redo");
//		menuItem2.addEventHandler(MouseEvent.MOUSE_CLICKED, controlur);
//		
//		menu1.getItems().add(menuItem1);
//		menu1.getItems().add(menuItem2);
//
//		menuBar.getMenus().add(menu1);
		fillGrid();
		BorderPane root = new BorderPane();
		root.setCenter(feld);
//		root.setTop(menuBar);

		HBox h = new HBox();
		Button b = new Button("Undo");
		b.setId("UNDO");
		b.addEventHandler(MouseEvent.MOUSE_CLICKED, controlur);
		h.getChildren().add(b);
		Button r = new Button("Redo");
		r.setId("REDO");
		r.addEventHandler(MouseEvent.MOUSE_CLICKED, controlur);
		h.getChildren().add(r);
		Button s = new Button("Save");
		s.setId("Save");
		s.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				System.out.println("save");
				try {
					FieldSerializer.save(
							"C:\\Users\\myhpn\\OneDrive - HTL Anichstrasse (1)\\SCHULESWPJAVA\\minesweepermvc\\src\\txt\\out.txt",
							model.getMatrixDeepCopy());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
		h.getChildren().add(s);

		root.setTop(h);

		model.addObserver(this);
		primaryStage.setTitle("Minesweeper - Spiel");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		LogView l = new LogView(model);
		primaryStage.setOnCloseRequest(event -> {
			l.close();
		});
	}

	@Override
	public void update(Observable obs, Object message) {
		MinesweeperMessage msg = (MinesweeperMessage) message;
		Button b = (Button) (getNodeByRowColumnIndex(msg.getY(), msg.getX(), feld));
		if (msg.getAction().equals(MinesweeperMessage.ACTIONS.NEW_FIELD)) {
			b = (Button) (getNodeByRowColumnIndex(msg.getY(), msg.getX(), feld));
			if (!msg.isOpen()) {
				setImg(b, IMG_HIDDEN);
				b.setStyle("-fx-background-color: #E3E3E3;");
				b.setText("");
				b.setVisible(true);
				b.setDisable(false);
			}

			else if (msg.isFlag())
				setImg(b, IMG_FLAG);
			else {
				b.setGraphic(null);
				b.setText(Integer.toString(msg.getNeighbourBombs()));
				switch (msg.getNeighbourBombs()) {
				case 0:
					b.setDisable(true);
					b.setText("");
					break;
				case 1:
					b.setStyle("-fx-background-color: #f4f4f8");
					break;
				case 2:
					b.setStyle("-fx-background-color: #fed766");
					break;
				case 3:
					b.setStyle("-fx-background-color: #2ab7ca");
					break;
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
					b.setStyle("-fx-background-color: #fe4a49");
					break;
				}
			}

		}

		if (msg.getAction().equals(MinesweeperMessage.ACTIONS.CELL_SET)) {
			b = (Button) (getNodeByRowColumnIndex(msg.getY(), msg.getX(), feld));
			b.setGraphic(null);
			b.setText(Integer.toString(msg.getNeighbourBombs()));
			switch (msg.getNeighbourBombs()) {
			case 0:
				b.setDisable(true);
				b.setText("");
				break;
			case 1:
				b.setStyle("-fx-background-color: #f4f4f8");
				break;
			case 2:
				b.setStyle("-fx-background-color: #fed766");
				break;
			case 3:
				b.setStyle("-fx-background-color: #2ab7ca");
				break;
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
				b.setStyle("-fx-background-color: #fe4a49");
				break;
			}
		} else if (msg.getAction().equals(MinesweeperMessage.ACTIONS.LOST)) {
			b.setText("");
			setImg(b, IMG_BOMB);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Minesweeper");
			alert.setHeaderText(null);
			alert.setContentText("Du hast verloren! Das Spiel wird neu gestartet");
			alert.showAndWait();
			restart();
		} else if (msg.getAction().equals(MinesweeperMessage.ACTIONS.FLAG_ON)) {
			setImg(b, IMG_FLAG);
		} else if (msg.getAction().equals(MinesweeperMessage.ACTIONS.FLAG_OFF)) {
			setImg(b, IMG_HIDDEN);
		} else if (msg.getAction().equals(MinesweeperMessage.ACTIONS.WON)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Minesweeper");
			alert.setHeaderText(null);
			alert.setContentText("Du hast gewonnen! Das Spiel wird neue gestartet");

			alert.showAndWait();
			restart();
		}
	}

	public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
		Node result = null;
		ObservableList<Node> children = gridPane.getChildren();

		for (Node node : children) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
				result = node;
				break;
			}
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		NormalStrategy strategy = new NormalStrategy();
		Playground model = Playground.instance(strategy, FieldSerializer.load(
				"C:\\Users\\myhpn\\OneDrive - HTL Anichstrasse (1)\\SCHULESWPJAVA\\minesweepermvc\\src\\txt\\test.txt"));
		model.toStringA();
		MinesweeperControl control = new MinesweeperControl(model);
		MinesweeperView view = new MinesweeperView(model, control);
		Platform.runLater(() -> {
			try {
				view.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}