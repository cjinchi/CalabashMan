package creature;

import field.BattleField;
import javafx.application.Platform;
import javafx.scene.image.Image;

import java.util.Random;

public class PCCreature extends  Creature implements Runnable {
    protected PCCreature(Image image, String name) {
        super(image, name);
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1500);
                Platform.runLater(()->{moveLeft();});
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
