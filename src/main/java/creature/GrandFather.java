package creature;

import creature.Creature;
import javafx.scene.image.Image;
import ui.ImageLoader;

public class GrandFather extends PlayerCreature {
    /**
     * Please use getInstance() to get GrandFather
     * @param image Sprite Image
     * @param name Name
     */
    private GrandFather(Image image, String name) {
        super(image, name);
    }

    private static GrandFather gf;
    public static GrandFather getInstance(){
        if(gf == null){
            gf = new GrandFather(ImageLoader.getImage("GrandFather"),"爷爷");
        }
        return gf;
    }
}
