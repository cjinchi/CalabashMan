package creature;

import creature.Creature;

public class GrandFather extends Creature {
    /**
     * Please use getInstance() to get GrandFather
     * @param spriteFileName Image url
     * @param name Name
     */
    private GrandFather(String spriteFileName, String name) {
        super(spriteFileName, name);
    }

    private static GrandFather gf;
    public static GrandFather getInstance(){
        if(gf == null){
            gf = new GrandFather("grandfather.png","爷爷");
        }
        return gf;
    }
}
