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

import static ru.spbau.bachelors2015.blackjack.Server.Status.LOSE;
import static ru.spbau.bachelors2015.blackjack.Server.Status.WIN;

public class GUI extends Application {
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

    private Server server = new ServerCommunicator();

    private ListView<Text> cardsList = new ListView<>();

    private final static String WON_TEXT = "You won";

    private final static String LOST_TEXT = "You lost";

    private void initMoreButton() {
        more = new Button(MORE_TEXT);
        more.setOnAction(onMoreClick);
    }

    private void initFinishButton() {
        finish = new Button(FINISH_TEXT);
        finish.setOnAction(onFinishClick);
    }

    private void initScoreLabel() {
        scoreText = new Text("0");
    }

    private void initStateHBox() {
        stateHBox = new HBox();
        stateHBox.getChildren().add(scoreText);
        stateHBox.getChildren().add(more);
        stateHBox.getChildren().add(finish);
        stateHBox.setAlignment(Pos.CENTER_RIGHT);
    }

    private void initGameVBox() {
        gameVBox = new VBox();
        gameVBox.getChildren().add(cardsList);
        gameVBox.getChildren().add(stateHBox);
        gameVBox.setAlignment(Pos.CENTER);
    }

    private void initStage() {
        initMoreButton();
        initFinishButton();
        initScoreLabel();
        initStateHBox();
        initGameVBox();

        stage.setScene(new Scene(gameVBox, WIDTH, HEIGHT));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        server.connect();

        initStage();
        while (!server.isStarted());

        stage.show();
    }

    private void serverPass() {
        while (server.status() != Server.Status.YOUR_TURN);

        server.pass();
    }

    private Card serverNextCard() {
        while (server.status() != Server.Status.YOUR_TURN);

        return server.nextCard();
    }

    private final EventHandler<ActionEvent> onMoreClick = event ->  {
        Card newCard = serverNextCard();
        scoreText.setText(String.valueOf(server.myPoints()));

        Text cardText = new Text(newCard.toString());
        ObservableList<Text> observableList = cardsList.getItems();
        observableList.add(cardText);
        cardsList.setItems(observableList);

        if (server.myPoints() >= Game.MAX_POINTS) {
            more.setDisable(true);
        }
    };

    private final EventHandler<ActionEvent> onFinishClick = event -> {
        serverPass();
        more.setDisable(true);
        finish.setDisable(true);
        onGameFinish();
    };

    private boolean serverWaitForGameFinish() {
        while (true) {
            Server.Status status = server.status();
            if (status == WIN) {
                return true;
            }

            if (status == LOSE) {
                return false;
            }
        }
    }

    public void onGameFinish() {
        boolean won = serverWaitForGameFinish();

        Text resText = new Text();
        if (won) {
            resText.setText(WON_TEXT);
        } else {
            resText.setText(LOST_TEXT);
        }
        Text scoreText = new Text();
        scoreText.setText(String.valueOf(server.myPoints()) + ":"
                + String.valueOf(server.hisPoints()));
        VBox resBox = new VBox();
        resBox.getChildren().add(resText);
        resBox.getChildren().add(scoreText);
        resBox.setAlignment(Pos.CENTER);
        Scene resScene = new Scene(resBox, WIDTH, HEIGHT);
        stage.setScene(resScene);

        server.disconnect();
    }
}
