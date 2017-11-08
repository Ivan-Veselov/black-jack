package ru.spbau.bachelors2015.blackjack;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

import static ru.spbau.bachelors2015.blackjack.CardRank.*;
import static ru.spbau.bachelors2015.blackjack.CardRank.TEN;
import static ru.spbau.bachelors2015.blackjack.Suit.HEARTS;
import static ru.spbau.bachelors2015.blackjack.Suit.SPADES;

public class GUI extends Application{
    private static final int HEIGHT = 320;

    private static final int WIDTH = 320;

    private final static String MORE_TEXT = "More";

    private final static String FINISH_TEXT = "Finish";

    private Stage stage;

    private Button more;

    private Button finish;

    private HBox stateHBox;

    private VBox gameVBox;

    private Text scoreText;

    private Game game;

    private ListView<Text> cardsList;


    private void initStage() {
        more = new Button(MORE_TEXT);
        more.setOnAction(onMoreClick);
        finish = new Button(FINISH_TEXT);
        finish.setOnAction(onFinishClick);
        scoreText = new Text("0");

        stateHBox = new HBox();
        stateHBox.getChildren().add(scoreText);
        stateHBox.getChildren().add(more);
        stateHBox.getChildren().add(finish);
        stateHBox.setAlignment(Pos.CENTER_RIGHT);
        cardsList = new ListView<>();
        //cardsList.setOrientation(Orientation.HORIZONTAL);
        gameVBox = new VBox();
        gameVBox.getChildren().add(cardsList);
        gameVBox.getChildren().add(stateHBox);
        gameVBox.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(gameVBox, WIDTH, HEIGHT));
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        game = new Game();
        stage = primaryStage;
        initStage();
    }

    private final EventHandler<ActionEvent> onMoreClick = event ->  {
        Card newCard = game.nextCard();
        scoreText.setText(String.valueOf(game.playerPoints()));
        if (game.playerPoints() > game.MAX_POINTS) {
            game.pass();
        }
        Text cardText = new Text(newCard.toString());
        ObservableList<Text> observableList = cardsList.getItems();
        observableList.add(cardText);
        cardsList.setItems(observableList);
    };

    private final EventHandler<ActionEvent> onFinishClick = event -> {
        game.pass();
        more.setDisable(true);
        finish.setDisable(true);
        onGameFinish(true);
    };

    public void onGameFinish(boolean won) {
        final String WON = "You won";
        final String LOST = "You lost";
        Text resText = new Text();
        if (won) {
            resText.setText(WON);
        } else {
            resText.setText(LOST);
        }
        Text scoreText = new Text();
        scoreText.setText(String.valueOf(game.playerPoints()) + ":"
                + String.valueOf(game.computerPoints()));
        VBox resBox = new VBox();
        resBox.getChildren().add(resText);
        resBox.getChildren().add(scoreText);
        resBox.setAlignment(Pos.CENTER);
        Scene resScene = new Scene(resBox, WIDTH, HEIGHT);
        stage.setScene(resScene);
    }
}
