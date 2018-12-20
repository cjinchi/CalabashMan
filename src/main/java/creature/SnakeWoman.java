package creature;

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
        //TODO
        System.out.println("snake");
    }
}
