package ui;

import app.Main;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CreatureSprite {
    public ImageView getProfileImage() {
        return profileImage;
    }

    public ProgressBar getHpBar() {
        return hpBar;
    }

    private ImageView profileImage;
    private ProgressBar hpBar;

    public static int UNIT_LENGTH = Main.UNIT_LENGTH;
    private final double hpBarWidth = UNIT_LENGTH*0.8;

    public CreatureSprite(Image image){
        profileImage = new ImageView();
        profileImage.setImage(image);
        profileImage.setFitWidth(UNIT_LENGTH);
        profileImage.setFitHeight(UNIT_LENGTH);

        hpBar = new ProgressBar();
        hpBar.setPrefWidth(hpBarWidth);
        hpBar.setProgress(1.0);
    }

    public void moveTo(int x,int y){
        profileImage.setX(x*UNIT_LENGTH);
        profileImage.setY(y*UNIT_LENGTH);
        hpBar.setTranslateX(x*UNIT_LENGTH+(UNIT_LENGTH-hpBarWidth)/2);
        hpBar.setTranslateY(y*UNIT_LENGTH);
    }

    public void setHp(int hpValue,int hpMaxValue){
        hpBar.setProgress((double)hpValue/(double)hpMaxValue);
    }


}
