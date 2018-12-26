package creature;

import javafx.application.Platform;
import javafx.scene.image.Image;
import ui.ImageLoader;

public class SnakeWoman extends PCCreature{
    private SnakeWoman(Image image , String name) {
        super(image, name);
    }

    private static SnakeWoman sw;
    public static SnakeWoman getInstance(){
        if(sw == null){
            sw = new SnakeWoman(ImageLoader.getImage("SnakeWoman"),"蛇精");
        }
        return sw;
    }

    @Override
    public void run() {
        int num = 0;
        while (alive){
            try {
                Thread.sleep(500);
                num++;

                Creature[] nearbyCreatures = bf.getNearbyCreatures(this.x,this.y);
                int nearbyEnemyNum = getEnemyNum(nearbyCreatures);

                if(fightingTimer==null&& nearbyEnemyNum>0){
                    startFightingAnimation();
                }else if(fightingTimer!=null&& nearbyEnemyNum==0){
                    stopFightingAnimation();
                }

                if(nearbyEnemyNum>0){
                    for(Creature creature:nearbyCreatures){
                        if(creature instanceof PlayerCreature){
                            creature.decreaseHp(this,power/nearbyEnemyNum);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(fightingTimer!=null){
            stopFightingAnimation();
        }
    }
}
