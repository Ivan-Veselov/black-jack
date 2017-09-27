package ru.spbau.bachelors2015.blackjack;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application{
    public static final int MAX_SCORE = 21;
    private Stage stage;
    private Button more;
    private Button finish;
    private final String MORE_TEXT = "More";
    private final String FINISH_TEXT = "Finish";
    private HBox buttonHBox;
    private VBox gameVBox;
    private HBox gameHBox;
    private Text scoreText;
    private Game game;
    private int score;
    private ListView<Text> cardsList;

    private void initStage() {
        more = new Button(MORE_TEXT);
        more.setOnAction(onMoreClick);
        finish = new Button(FINISH_TEXT);
        finish.setOnAction(onFinishClick);

        buttonHBox = new HBox();
        buttonHBox.getChildren().add(more);
        buttonHBox.getChildren().add(finish);
        cardsList = new ListView<>();
        cardsList.setOrientation(Orientation.HORIZONTAL);
        gameVBox = new VBox();
        gameVBox.getChildren().add(cardsList);
        gameVBox.getChildren().add(buttonHBox);
        scoreText = new Text("0");
        score = 0;
        gameHBox = new HBox();
        gameHBox.getChildren().add(scoreText);
        gameHBox.getChildren().add(gameVBox);

        stage.setScene(new Scene(gameHBox));
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        game = new Game(null);
        stage = primaryStage;
        initStage();
    }

    private final EventHandler<ActionEvent> onMoreClick = event ->  {
        Card newCard = game.nextCard();
        score += newCard.rank().value();
        scoreText.setText(String.valueOf(score));
        if (score > MAX_SCORE) {
            game.pass();
        }
        Text cardText = new Text();
    };

    private final EventHandler<ActionEvent> onFinishClick = event -> {
        game.pass();
        more.setDisable(true);
        finish.setDisable(true);
    };
}
