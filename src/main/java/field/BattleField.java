package field;

import creature.Creature;
import ui.BattleFieldSprite;

public class BattleField {
    private BattleFieldSprite bfs;
    private Creature[][] creatures;
    private int width,height;

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
        if(isValidPosition(fromX,fromY)&&creatures[x][y]!=creature){
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

}
