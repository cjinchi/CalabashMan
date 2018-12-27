package notification;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ui.ImageLoader;

public class MainNotificationController extends NotificationController{
    private ImageView background = new ImageView(ImageLoader.getImage("NotificationBackground"));

    @Override
    public void show(String word) {
        text.setText(word);
    }

    @Override
    protected void afterDrawingText(Group root) {
        root.getChildren().add(background);
        background.setFitWidth(400);
        background.setFitHeight(300);
        double x = (widthUnit*unitLength-background.getFitWidth())/2;
        double y = (heightUnit*unitLength-background.getFitHeight())/2;
        background.setX(x);
        background.setY(y);
        text.setWrappingWidth(400);
        text.setX(x+50);
        text.setY(y+50);
        text.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 25));
        text.setFill(Color.BLACK);
        text.toFront();

    }
}
