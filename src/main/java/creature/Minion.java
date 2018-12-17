package creature;

import java.util.ArrayList;
import java.util.List;

public class Minion extends Creature {
    private Minion(String spriteFileName, String name) {
        super(spriteFileName, name);
    }
    private static final int TOTAL_NUM = 6;
    private static List<Minion> minions = new ArrayList<>();
    static {
        for (int i = 0; i < TOTAL_NUM; i++) {
            minions.add(null);
        }
    }
    public static Minion getInstance(int index){
        if(minions.get(index-1)==null){
            minions.set(index-1,new Minion("minion.png",String.format("喽啰%d",index)));
        }
        return minions.get(index-1);
    }

}
