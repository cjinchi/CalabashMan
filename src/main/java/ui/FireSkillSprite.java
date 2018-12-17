package ui;

import javafx.scene.image.ImageView;

public class FireSkillSprite {
    public ImageView getFireImage() {
        return fireImage;
    }

    private ImageView fireImage;

    public FireSkillSprite(String spriteFileName){
        fireImage = new ImageView();
    }

    public void setAngle(double angle){
        //TODO
    }

}
