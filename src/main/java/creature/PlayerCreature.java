package creature;

import javafx.application.Platform;
import javafx.scene.image.Image;

public class PlayerCreature extends Creature{
    protected PlayerCreature(Image image, String name) {
        super(image, name);
        maxhp = 200;
        hp = maxhp;
    }


    @Override
    protected int getEnemyNum(Creature[] creatures) {
        int num = 0;
        for(Creature creature:creatures){
            if(creature instanceof PCCreature){
                num ++;
            }
        }
        return num;
    }

    @Override
    public void run() {
        while (alive){
            try {
                Thread.sleep(500);

                Creature[] nearbyCreatures = bf.getNearbyCreatures(this.x,this.y);
                int nearbyEnemyNum = getEnemyNum(nearbyCreatures);

                if(fightingTimer==null&& nearbyEnemyNum>0){
                    startFightingAnimation();
                }else if(fightingTimer!=null&& nearbyEnemyNum ==0){
                    stopFightingAnimation();
                }

                if(nearbyEnemyNum>0){
                    for(Creature creature:nearbyCreatures){
                        if(creature instanceof PCCreature){
                            creature.decreaseHp(this,power/nearbyEnemyNum);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(getFightingTimer()!=null){
            stopFightingAnimation();
        }
    }
}
