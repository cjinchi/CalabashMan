package field;

import creature.Creature;
import creature.PCCreature;
import sun.java2d.pipe.SpanIterator;
import ui.BattleFieldSprite;

public class BattleField {
    private BattleFieldSprite bfs;
    private Creature[][] creatures;
    private int width,height;

    private Creature currentSelectCreature;

    public BattleField(int width,int height){
        bfs = new BattleFieldSprite(width,height);
        creatures = new Creature[width][height];
        this.width = width;
        this.height = height;
    }

    public BattleFieldSprite getBfs() {
        return bfs;
    }

    public synchronized boolean moveTo(Creature creature,int x,int y,int fromX,int fromY){
        if(!isValidPosition(x,y)){
            return false;
        }
        if(creatures[x][y]!=null){
            return false;
        }
        if(isValidPosition(fromX,fromY)&&creatures[fromX][fromY]!=creature){
            System.out.println(creatures[x][y].getName());
            System.out.println(creature.getName());
            return false;
        }
        creatures[x][y] = creature;
        if(isValidPosition(fromX,fromY)){
            creatures[fromX][fromY] = null;
        }
        return true;
    }

    private boolean isValidPosition(int x, int y){
        return x>=0&&x<width&&y>=0&&y<height;
    }

    public boolean enter(Creature creature,int x,int y){
        return moveTo(creature,x,y,-1,-1);
    }

    public void clickOn(int x,int y){
        if(!isValidPosition(x,y)){
            return;
        }
        if(creatures[x][y]==null||creatures[x][y] instanceof PCCreature){
            currentSelectCreature = null;
        }else{
            currentSelectCreature = creatures[x][y];
        }
    }

    public Creature getCurrentSelectCreature() {
        return currentSelectCreature;
    }
}