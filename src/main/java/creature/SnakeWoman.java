package creature;

public class SnakeWoman extends Creature{
    private SnakeWoman(String spriteFileName, String name) {
        super(spriteFileName, name);
    }

    private static SnakeWoman sw;
    public static SnakeWoman getInstance(){
        if(sw == null){
            sw = new SnakeWoman("snake.png","蛇精");
        }
        return sw;
    }

}
