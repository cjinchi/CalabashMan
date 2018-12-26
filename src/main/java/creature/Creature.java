package creature;

import app.Main;
import field.BattleField;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.image.Image;
import ui.CreatureSprite;

public abstract class Creature extends Thread{
    protected CreatureSprite sprite;
    private String name;
    protected BattleField bf;
    protected int x = -1;
    protected int y = -1;
    private boolean moving = false;
    protected int hp;
    protected int maxhp;
    protected int power = 10;
    protected AnimationTimer fightingTimer;
    protected boolean alive = true;

    public CreatureSprite getSprite() {
        return sprite;
    }

    public String getCreatureName() {
        return name;
    }

    protected Creature(Image image, String name) {
        this.sprite = new CreatureSprite(image);
        this.name = name;
    }

    public void moveTo(int newX,int newY){
        if(moving){
            return;
        }

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

                moving = true;

                final int xOldPixel = this.x*Main.UNIT_LENGTH;
                final int yOldPixel = this.y*Main.UNIT_LENGTH;
                final boolean selected = (bf.getCurrentSelectCreature()==this);
                new AnimationTimer() {
                    private final long startTime = System.nanoTime();
                    final int xDstPixel = newX*Main.UNIT_LENGTH;
                    final int yDstPixel = newY*Main.UNIT_LENGTH;
                    final int xDeltaPixel = (xDstPixel>xOldPixel?1:(xDstPixel==xOldPixel?0:-1));
                    final int yDeltaPixel = (yDstPixel>yOldPixel?1:(yDstPixel==yOldPixel?0:-1));

                    @Override
                    public void handle(long now) {

                        if(Math.abs(sprite.getXPixel()- xDstPixel)<10&&Math.abs(sprite.getYPixel()- yDstPixel)<10){
                            Platform.runLater(()->{
                                sprite.moveToByPixel(xDstPixel, yDstPixel);
                                if(selected){
                                    bf.getBfs().getOutline().moveToByPixel(xDstPixel,yDstPixel);
                                }
                                moving = false;
                            });
                            this.stop();
                        }else{
                            int deltaTime = (int)((now-startTime)/5000000);
                            Platform.runLater(()->{
                                sprite.moveToByPixel(xOldPixel+deltaTime* xDeltaPixel,yOldPixel+deltaTime* yDeltaPixel);
                                if(selected){
                                    bf.getBfs().getOutline().moveToByPixel(xOldPixel+deltaTime* xDeltaPixel,yOldPixel+deltaTime* yDeltaPixel);
                                }
                            });
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

    public void moveUp(){
        moveTo(x,y-1);
    }

    public void moveDown(){
        moveTo(x,y+1);
    }

    public void moveLeft(){
        moveTo(x-1,y);
    }

    public void moveRight(){
        moveTo(x+1,y);
    }

    protected abstract int getEnemyNum(Creature[] creatures);

    protected synchronized void startFightingAnimation(){
        if(getFightingTimer()!=null){
            throw new RuntimeException();
        }
        setFightingTimer(new AnimationTimer() {
            @Override
            public void handle(long now) {
                //I Love Math
                Platform.runLater(()->{sprite.getProfileImage().setRotate(Math.sin(now/100000000)*20);});
            }
        });
        getFightingTimer().start();
    }

    protected synchronized void stopFightingAnimation(){
        if(getFightingTimer()==null){
//            throw new RuntimeException();
            return;
        }
        Platform.runLater(()->{sprite.getProfileImage().setRotate(0);});
        getFightingTimer().stop();
        setFightingTimer(null);
    }

    public synchronized boolean isMoving(){
        return moving;
    }

    public synchronized AnimationTimer getFightingTimer() {
        return fightingTimer;
    }

    private synchronized void setFightingTimer(AnimationTimer timer){
        this.fightingTimer = timer;
    }

    public void decreaseHp(Creature creature,int n){
        int newHp = hp - n;
        hp = newHp>0?newHp:0;
        Platform.runLater(()->{sprite.setHp(hp,maxhp);});
        if(hp==0){
            becomeDead();
            System.out.println(creature.getCreatureName()+" 杀死了 "+this.getCreatureName());
        }
    }

    private void becomeDead(){
        //remove myself from battle filed
        bf.getOutOfField(this,x,y);
        //stop any animation or behavior
        alive = false;
        //turn sprite into dead state
        sprite.becomeDead();
        //remove select
        bf.deselectCurrentSelectCreature(this);
        bf.getBfs().toBack();


    }
}
