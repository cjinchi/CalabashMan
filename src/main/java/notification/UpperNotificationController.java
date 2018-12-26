package notification;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class UpperNotificationController extends NotificationController{
    private AnimationTimer timer;

    @Override
    public void show(String word) {
        text.setText(word);
        if(timer!=null){
            timer.stop();
        }
        final long startTime = System.nanoTime();
        timer = new AnimationTimer() {
            private boolean sleeping = false;
            private long sleepingStartTime;
            private final long TOTAL_SLEEP_TIME = Long.valueOf("2000000000");
            @Override
            public void handle(long now) {
                if(!sleeping){
                    double newY = (now - startTime)/7000000-30;
                    if(Math.abs(newY-30)<3){
                        Platform.runLater(()->{text.setY(40);});
                        sleeping = true;
                        sleepingStartTime = System.nanoTime();
                    }else{
                        Platform.runLater(()->{text.setY(newY);});
                    }
                }else{
                    if(now-sleepingStartTime>TOTAL_SLEEP_TIME){
                        stop();
                        Platform.runLater(()->{text.setY(-30);});
                        timer = null;
                    }
                }

            }
        };
        timer.start();
    }

    @Override
    protected void afterDrawing() {
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, 34));
        text.setFill(Color.RED);
        text.setStroke(Color.BLACK);
//        text.setText("大娃 击败 喽啰1");
        text.setWrappingWidth(300);
        text.setX((widthUnit*unitLength-300)/2);
        text.setY(-30);
    }
}
