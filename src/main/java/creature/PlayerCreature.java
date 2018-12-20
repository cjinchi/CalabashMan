package creature;

import field.BattleField;
import javafx.application.Platform;
import javafx.scene.image.Image;

public class PlayerCreature extends Creature{
    protected PlayerCreature(Image image, String name) {
        super(image, name);
    }

    @Override
    protected int checkNearbyEnemy() {
        Creature[] nearby = bf.getNearbyCreatures(this.x,this.y);
        int num = 0;
        for(Creature creature:nearby){
            if(creature instanceof PCCreature){
                num ++;
            }
        }
        return num;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(500);
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
