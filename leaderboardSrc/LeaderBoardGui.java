import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LeaderBoardGui extends Application{

	private GridPane grid;
	private Button create;
	private Button add;
	private GridPane buttons;
	private GridPane list;
	private ScrollPane scrolling;
	private Button newGame;

	
	// start is the method called to launch the window. The stage is the window
	// and the scene is what is actually displayed in the window. You have one
	// stage and as many scenes as you want, but you can only have one on the
	// stage at once. Just like a play and real stage.
	@Override
	public void start(Stage primaryStage){
		// A GridPane is a layout manager. It makes it really easy to align
		// things. You can put GridPanes inside GridPanes to get even more
		// control over alignment.
		grid = new GridPane();
		buttons = new GridPane();
		list = new GridPane();
		
		// This will cause a scroll bar to automatically appear when the list of
		// players goes beyond the end of the stage. JavaFX is great!
		scrolling = new ScrollPane(list);
		scrolling.setPrefHeight(300);
		
		// You can set the width of a column in a GridPane this way. It is
		// similar for row height
		list.getColumnConstraints().add(new ColumnConstraints(50));
		list.getColumnConstraints().add(new ColumnConstraints(200));
		list.getColumnConstraints().add(new ColumnConstraints(50));
		
		addPlayers(list);
		
		// You can use lambda functions for event handlers which is much nicer
		// than the swing way where you have to have a specific handler object
		create = new Button("Reset Leaderboard");
		create.setOnMouseClicked(event -> createLeaderboard());
		add = new Button ("Add player");
		add.setOnMouseClicked(event -> newPlayerDialog());
		newGame = new Button("Enter Results");
		newGame.setOnMouseClicked(event -> enterNewGame());
		
		DBCreator creator = new DBCreator();
		if (!creator.tableExists("leaderboard")){
			add.setDisable(true);
			newGame.setDisable(true);
			create.setText("Create Leaderboard");
		}

		// GridPane lets you add things at specific grid locations. The previous
		// locations don't even need to be filled first
		buttons.add(create, 0, 0);
		buttons.add(add, 1, 0);
		buttons.add(newGame, 2, 0);
		
		grid.add(buttons, 0, 0);
		grid.add(scrolling, 0, 1);
		
		Scene scene = new Scene(grid);
		primaryStage.setScene(scene);
		
		primaryStage.setResizable(false);
		
		primaryStage.show();
	}
	
	private void enterNewGame() {
		// Creating new custom pop-up dialog
		Dialog<String[]> dialog = new Dialog<>();
		dialog.setTitle("Enter Results");
		
		//Add two buttons to the dialog
		ButtonType addGameButtonType = new ButtonType("Add Game", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(addGameButtonType, ButtonType.CANCEL);

		GridPane g = new GridPane();
		g.setHgap(10);
		g.setVgap(10);
		g.setPadding(new Insets(20, 150, 10, 10));

		ArrayList<String> names = new ArrayList<String>();
		DBCommands database = new DBCommands();
		ArrayList<String> players = database.select();
		for (int i = 0; i < players.size(); i++){
			String[] player = players.get(i).split(",");
			names.add(player[1]);
		}
		
		// ChoiceBox = drop down menu
		ChoiceBox<String> firstPlayerChoiceBox = new ChoiceBox<String>(FXCollections.observableArrayList(names));
		ChoiceBox<String> secondPlayerChoiceBox = new ChoiceBox<String>(FXCollections.observableArrayList(names));
		ChoiceBox<String> winnerChoiceBox = new ChoiceBox<String>(FXCollections.observableArrayList("First Player", "Second Player", "Tie"));
		
		g.add(new Label("First Player:"), 0, 0);
		g.add(firstPlayerChoiceBox, 1, 0);
		g.add(new Label("Second Player:"), 0, 1);
		g.add(secondPlayerChoiceBox, 1, 1);
		g.add(new Label("Select Winner:"), 0, 2);
		g.add(winnerChoiceBox, 1, 2);
		
		// More lambda functions!
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == addGameButtonType) {
				String winner = winnerChoiceBox.getSelectionModel().getSelectedItem();
				String player1 = firstPlayerChoiceBox.getSelectionModel().getSelectedItem();
				String player2 = secondPlayerChoiceBox.getSelectionModel().getSelectedItem();
				if (winner == null || player1 == null || player2 == null)
					return null;
				if (winner.equals("Tie"))
					return new String[] { player1, player2 };
				else if (winner == "First Player")
					return new String[] { player1 };
				else
					return new String[] { player2 };
			}
			return null;
		});
		
		// See ChessGUI.promoteDialog() for explanation of Optional and ifPresent
		dialog.getDialogPane().setContent(g);
		Optional<String[]> result = dialog.showAndWait();
		result.ifPresent(winners -> updateScores(winners));

	}

	private void updateScores(String[] winners) {
		DBCommands database = new DBCommands();
		if (winners.length==1){
			database.update(winners[0].trim(), 2);
		}
		else{
			database.update(winners[0].trim(), 1);
			database.update(winners[1].trim(), 1);
		}
		addPlayers(list);
	}

	private void addPlayers(GridPane list){
		list.getChildren().removeAll(list.getChildren());
		list.add(new Label("Rank"), 0, 0);
		list.add(new Label("Name"), 1, 0);
		list.add(new Label("Points"), 2, 0);
		if (!new DBCreator().tableExists("leaderboard")) return;
		DBCommands database = new DBCommands();
		ArrayList<String> players = database.select();
		Collections.sort(players);
		Collections.reverse(players);
		for (int i = 0; i < players.size(); i++){
			String[] player = players.get(i).split(",");
			list.add(new Label((i+1)+"."), 0, i+1);
			list.add(new Label(player[1]), 1, i+1);
			list.add(new Label(player[0]), 2, i+1);
		}
		
		// No need to call repaint() like in swing! Javafx takes care of it for you
	}

	private void newPlayerDialog() {
		DBCommands database = new DBCommands();
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Add Player");
		dialog.setContentText("Enter new user:");

		Optional<String> result = dialog.showAndWait();
		result.ifPresent(name -> database.insert(name));
		
		addPlayers(list);
	}

	private void createLeaderboard() {
		DBCreator creator = new DBCreator();
		if (creator.tableExists("leaderboard"))
			creator.delete();
		creator.create();
		
		add.setDisable(false);
		newGame.setDisable(false);
		create.setText("Reset Leaderboard");
		
		addPlayers(list);
	}
	
	// This launches the gui. If you want the main method in a different class
	// use Application.launch(LeaderBoardGui.class, args); instead of just
	// launch(args); This is a little unfortunate in my opinion, but it's
	// definitely worth putting up with this drawback because the rest of Javafx
	// is so nice.
	public static void main(String[] args){
		launch(args);
	}
	

}
