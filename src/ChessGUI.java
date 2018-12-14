
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChessGUI extends Application implements EndObserver {

	// the following constants are for CHESSBOARD.png
	public static final String BOARD_PATH = "/Images/CHESSBOARD.png";
	public static final double SCALE_FACTOR = 0.8;
	public static final int SQUARE_SIDE = 103;
	public static final int LEFT_BORDER = 74;
	public static final int TOP_BORDER = 83;
	public static final int DEFAULT_BOARD_SIZE = 1000;

	private int[] fromLocation = null; // stores starting location of piece
										// being picked up
	private Board board;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) {
		ImageView boardView = new ImageView();
		board = new Board(new TraditionalStart());
		board.addObserver(this);

		Group pieces = new Group();
		Pane root = new Pane();
		Scene scene = new Scene(root);

		root.getChildren().add(boardView);
		root.getChildren().add(pieces);

		// loops through board and sets up GUI according to state of board
		for (char i = 'a'; i <= 'h'; i++) {
			for (int j = 1; j <= 8; j++) {
				ChessPiece piece = board.pieceAt(i, j);
				if (piece != null) {
					Image image = ImageGenerator.getImage(piece.getType(), piece.getColor());
					piece.setImage(image);
					piece.setFitHeight(SQUARE_SIDE * SCALE_FACTOR);
					piece.setX((LEFT_BORDER + SQUARE_SIDE * (i - 'a')) * SCALE_FACTOR);
					piece.setY((TOP_BORDER + SQUARE_SIDE * (8 - j)) * SCALE_FACTOR);
					piece.setOnMousePressed(onMousePressed);
					piece.setOnMouseDragged(onMouseDragged);
					piece.setOnMouseReleased(onMouseReleased);
					pieces.getChildren().add(piece);
				}
			}
		}
		/*
		 * we are using a pane to cover up captured pieces. This prevent players
		 * from dragging pieces.
		 */
		Pane capturePane = new Pane();
		capturePane.setPrefWidth(4 * SQUARE_SIDE * SCALE_FACTOR);
		capturePane.setPrefHeight(SCALE_FACTOR * DEFAULT_BOARD_SIZE);
		capturePane.relocate(DEFAULT_BOARD_SIZE * SCALE_FACTOR, 0);

		root.getChildren().add(capturePane);
		boardView.setImage(new Image(BOARD_PATH));
		boardView.setFitHeight(DEFAULT_BOARD_SIZE * SCALE_FACTOR);
		boardView.setPreserveRatio(true);

		stage.setTitle("Chess!");
		stage.setScene(scene);
		stage.setWidth(SCALE_FACTOR * (DEFAULT_BOARD_SIZE + 4 * SQUARE_SIDE));
		stage.show();
		stage.setResizable(false);

		pieces.toFront();
		boardView.toBack();
		capturePane.toFront();
	}

	EventHandler<MouseEvent> onMousePressed = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			ImageView imView = (ImageView) event.getSource();
			imView.toFront();
			fromLocation = getClosestSquare(event.getSceneX(), event.getSceneY());
		}
	};

	EventHandler<MouseEvent> onMouseDragged = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			double x = event.getSceneX();
			double y = event.getSceneY();
			ImageView imView = (ImageView) event.getSource();
			imView.relocate(x - SCALE_FACTOR * SQUARE_SIDE / 2, y - SCALE_FACTOR * SQUARE_SIDE / 2);
			event.consume();
		}
	};

	EventHandler<MouseEvent> onMouseReleased = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			ImageView imView = (ImageView) event.getSource();
			int[] closestSquare = getClosestSquare(event.getSceneX(), event.getSceneY());
			boolean isOutOfBound = checkBoundary(closestSquare[0], closestSquare[1]);

			if (!isOutOfBound) {
				double[] closestSquareCoord = getClosestSquareCoord(closestSquare[0], closestSquare[1]);
				imView.relocate(closestSquareCoord[0], closestSquareCoord[1]);
				board.makeMove(fromLocation[0], fromLocation[1], closestSquare[0], closestSquare[1]);
				if (board.canPromote())
					promoteDialog((ChessPiece) event.getSource());
			} else {
				double[] backCoord = getClosestSquareCoord(fromLocation[0], fromLocation[1]);
				imView.relocate(backCoord[0], backCoord[1]);
			}
			fromLocation = null;
			imView.toBack();
		}
	};

	private boolean checkBoundary(int col, int row) {
		return col < 0 || col > 7 || row < 0 || row > 7;
	}

	public void promoteDialog(ChessPiece moving) {
		ArrayList<String> choices = new ArrayList<String>();
		choices.add("Bishop");
		choices.add("Knight");
		choices.add("Queen");
		choices.add("Rook");

		ChoiceDialog<String> dialog = new ChoiceDialog<String>("Bishop", choices);
		dialog.setTitle("Promote");
		dialog.setHeaderText(null);
		dialog.setContentText("Choose your promotion:");

		// Optional is a container that can hold a null or non-null
		Optional<String> result = dialog.showAndWait();
		// ifPresent sets a Consumer object to be run when the the value becomes
		// not-null. In this case the Consumer is a lambda function with
		// String parameter type and which calls moving.promote
		result.ifPresent(type -> moving.promote(type));
	}

	// converts square coordinate on chess board to coordinate in GUI
	public static double[] getClosestSquareCoord(int x, int y) {
		double[] closestSquareCoord = new double[2];
		closestSquareCoord[0] = SCALE_FACTOR * (LEFT_BORDER + x * SQUARE_SIDE);
		closestSquareCoord[1] = SCALE_FACTOR * (TOP_BORDER + (7 - y) * SQUARE_SIDE);
		return closestSquareCoord;
	}

	/*
	 * returns column and row numbers of given position the bottom left corner
	 * is position (0,0), and top right is (7,7)
	 */
	public int[] getClosestSquare(double x, double y) {
		int[] closestSquare = new int[2];
		double adjX = x / SCALE_FACTOR;
		double adjY = y / SCALE_FACTOR;
		closestSquare[0] = ((int) adjX - LEFT_BORDER) / SQUARE_SIDE;
		closestSquare[1] = 7 - ((int) adjY - TOP_BORDER) / SQUARE_SIDE;
		return closestSquare;
	}

	// converts algebraic notation to coordinates in GUI
	public static double[] algebraicToGUI(char col, int row) {
		double[] coord = new double[2];
		int colAdj = col - 'a';
		int rowAdj = 8 - row;
		coord[0] = SCALE_FACTOR * (LEFT_BORDER + colAdj * SQUARE_SIDE);
		coord[1] = SCALE_FACTOR * (TOP_BORDER + rowAdj * SQUARE_SIDE);
		return coord;
	}

	@Override
	public void update(ChessEnd endType) {
		if (endType == null)
			return;
		switch (endType) {
		case CHECK:
			checkAlert();
			break;
		case STALEMATE:
			stalemateAlert();
			break;
		case WHITE_WINS:
			whiteAlert();
			break;
		case BLACK_WINS:
			blackAlert();
			break;
		default:
			return;
		}

	}

	private void blackAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game Over");
		alert.setHeaderText(null);
		alert.setContentText("Black Wins!");
		alert.showAndWait();
	}

	private void whiteAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game Over");
		alert.setHeaderText(null);
		alert.setContentText("White Wins!");
		alert.showAndWait();
	}

	private void stalemateAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game Over");
		alert.setHeaderText(null);
		alert.setContentText("Stalemate");
		alert.showAndWait();
	}

	private void checkAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Check");
		alert.setHeaderText(null);
		alert.setContentText("Check!");
		alert.showAndWait();
	}
}
