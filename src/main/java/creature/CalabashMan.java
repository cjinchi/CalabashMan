package creature;

import java.util.ArrayList;
import java.util.List;

public class CalabashMan extends Creature{
    /**
     * Please use getInstance() to get specific CalabashMan
     * @param spriteFileName Image url
     * @param name Name
     */
    private CalabashMan(String spriteFileName, String name) {
        super(spriteFileName, name);
    }

    private static List<CalabashMan> brothers = new ArrayList<>();
    static {
        for (int i = 0; i < 7; i++) {
            brothers.add(null);
        }
    }

    /**
     * Get specific CalabashMan
     * @param index Range between 1 and 7
     * @return CalabashMan index-th oldest
     */
    public static CalabashMan getInstance(int index){
        if (index < 1 || index > 7) {
            throw new RuntimeException("Invalid CalabashMan index.");
        } else {
            if (brothers.get(index - 1) == null) {
                String name = "";
                switch (index) {
                    case 1:
                        name = "大娃";
                        break;
                    case 2:
                        name = "二娃";
                        break;
                    case 3:
                        name = "三娃";
                        break;
                    case 4:
                        name="四娃";
                        break;
                    case 5:
                        name="五娃";
                        break;
                    case 6:
                        name = "六娃";
                        break;
                    case 7:
                        name="七娃";
                        break;
                }
                brothers.set(index-1,new CalabashMan(String.format("%d.png",index),name));
            }
            return brothers.get(index - 1);
        }
    }
}
