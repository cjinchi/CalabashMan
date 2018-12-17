package ui;

import app.Main;
import javafx.scene.control.ProgressBar;
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

    public CreatureSprite(String spriteFileName){
        profileImage = new ImageView(spriteFileName);
        profileImage.setFitWidth(Main.UNIT_LENGTH);
        profileImage.setFitHeight(Main.UNIT_LENGTH);

        hpBar = new ProgressBar();
        hpBar.setPrefWidth(Main.UNIT_LENGTH);
    }

    public void moveTo(int x,int y){
        profileImage.setX(x);
        profileImage.setY(y);
        hpBar.setTranslateX(x);
        hpBar.setTranslateY(y);
    }

    public void setHp(int hpValue,int hpMaxValue){
        hpBar.setProgress((double)hpValue/(double)hpMaxValue);
    }


}
