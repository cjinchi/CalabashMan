package creature;

import field.BattleField;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.image.Image;

import java.util.Random;

public class PCCreature extends  Creature {
    protected PCCreature(Image image, String name) {
        super(image, name);
    }

    @Override
    protected int checkNearbyEnemy() {
        Creature[] nearby = bf.getNearbyCreatures(this.x,this.y);
        int num = 0;
        for(Creature creature:nearby){
            if(creature instanceof PlayerCreature){
                num ++;
            }
        }
        return num;
    }

    @Override
    public void run() {
        int num = 0;
        while (true){
            try {
                Thread.sleep(500);
                num++;
                if(num >= 3){
                    num = 0;
                    Platform.runLater(()->{moveLeft();});
                }
                if(fightingTimer==null&&checkNearbyEnemy()>0){
                    startFightingAnimation();
                }else if(fightingTimer!=null&&checkNearbyEnemy()==0){
                    stopFightingAnimation();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
