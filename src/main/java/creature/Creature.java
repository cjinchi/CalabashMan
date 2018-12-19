package creature;

import field.BattleField;
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
        if(this.x<0||this.y<0){
            successful = bf.enter(this,newX,newY);
        }else{
            successful = bf.moveTo(this,newX,newY,this.x,this.y);
        }
        if(successful){
            this.x=newX;
            this.y = newY;
            sprite.moveTo(newX,newY);
        }
    }

    public void enterBattleField(BattleField bf,int x,int y){
        this.bf = bf;
        moveTo(x,y);
    }

}
