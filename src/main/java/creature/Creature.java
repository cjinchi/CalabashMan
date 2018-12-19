package creature;

import app.Main;
import field.BattleField;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import ui.CreatureSprite;

public class Creature {
    private CreatureSprite sprite;
    private String name;
    private BattleField bf;
    private int x = -1, y = -1;

    public CreatureSprite getSprite() {
        return sprite;
    }

    public String getName() {
        return name;
    }

    protected Creature(Image image, String name) {
        this.sprite = new CreatureSprite(image);
        this.name = name;
    }

    public void moveTo(int newX,int newY){
        boolean successful = false;
        boolean newHere = (this.x<0||this.y<0);
        if(newHere){
            successful = bf.enter(this,newX,newY);
        }else{
            successful = bf.moveTo(this,newX,newY,this.x,this.y);
        }
        if(successful){
            if(!newHere){
                //From here on,x and y are 'pixel-level'

                final int xOldPixel = this.x*Main.UNIT_LENGTH;
                final int yOldPixel = this.y*Main.UNIT_LENGTH;
                new AnimationTimer() {
                    private final long startTime = System.nanoTime();
                    final int xDstPixel = newX*Main.UNIT_LENGTH;
                    final int yDstPixel = newY*Main.UNIT_LENGTH;
                    final int xDeltaPixel = (xDstPixel>xOldPixel?1:(xDstPixel==xOldPixel?0:-1));
                    final int yDeltaPixel = (yDstPixel>yOldPixel?1:(yDstPixel==yOldPixel?0:-1));


                    @Override
                    public void handle(long now) {

                        if(Math.abs(sprite.getXPixel()- xDstPixel)<10&&Math.abs(sprite.getYPixel()- yDstPixel)<10){
                            sprite.moveToByPixel(xDstPixel, yDstPixel);
                            this.stop();
                        }else{
                            int deltaTime = (int)((now-startTime)/5000000);
                            sprite.moveToByPixel(xOldPixel+deltaTime* xDeltaPixel,yOldPixel+deltaTime* yDeltaPixel);
                        }
                    }
                }.start();
            }else {
                sprite.moveToByUnit(newX,newY);
            }
            this.x = newX;
            this.y = newY;
        }
    }

    public void enterBattleField(BattleField bf,int x,int y){
        this.bf = bf;
        moveTo(x,y);
    }

}
