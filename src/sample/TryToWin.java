package sample;

import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.*;
import java.util.List;

public class TryToWin {

    private Pane pane;
    private Random random;
    private List<Circle> targets;
    private int killedTargets = 0;
    private TextField score;

    public TryToWin (Pane pane) {
        score = new TextField();
        score.setDisable(true);
        killedTargets = 0;
        random = new Random();
        targets = new LinkedList<>();
        this.pane = pane;
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    createNewTarget();
                    if (targets.size() >= 10) {
                        timer.cancel();
                        Text text = new Text("ПРОИГРЫШ");
                        text.setFont(new Font(100));
                        text.setWrappingWidth(pane.getPrefWidth());
                        text.setX(0);
                        text.setY(pane.getPrefHeight() / 2);
                        text.setTextAlignment(TextAlignment.CENTER);
                        pane.getChildren().add(text);
                    }
                    if (killedTargets >= 100) {
                        timer.cancel();
                        Text text = new Text("ВЫИГРЫШ");
                        text.setFont(new Font(100));
                        text.setWrappingWidth(pane.getPrefWidth());
                        text.setX(0);
                        text.setY(pane.getPrefHeight() / 2);
                        text.setTextAlignment(TextAlignment.CENTER);
                        pane.getChildren().add(text);
                    }
                    pane.getChildren().remove(score);
                    pane.getChildren().add(score);
                    setScore();
                });
            }
        };
        timer.schedule(timerTask, 0, 400);

    }

    private void setScore() {
        score.setText("" + killedTargets);
    }

    private void createNewTarget() {
        Circle circle = new Circle(random.nextInt((int)pane.getPrefWidth() - 50) + 50, random.nextInt((int)pane.getPrefHeight() - 50) + 50, (int)((random.nextInt(201) + 50) / 2), newColor());
        targets.add(circle);
        pane.getChildren().remove(circle);
        pane.getChildren().add(circle);
        circle.setOnMouseClicked(e -> {
            killedTargets++;
            targets.remove(circle);
            pane.getChildren().remove(circle);
            setScore();
        });
    }

    private Color newColor() {
        int r = random.nextInt(10);
        if (r == 0)
            return Color.BLACK;
        else if (r == 1)
            return Color.BLUE;
        else if (r == 2)
            return Color.RED;
        else if (r == 3)
            return Color.BLACK;
        else if (r == 4)
            return Color.ORANGE;
        else if (r == 5)
            return Color.YELLOW;
        else if (r == 6)
            return Color.GREEN;
        else if (r == 7)
            return Color.BROWN;
        else if (r == 8)
            return Color.PURPLE;
        else
            return Color.PINK;
    }

}
