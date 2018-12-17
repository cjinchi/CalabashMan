package creature;

import ui.CreatureSprite;

public class Creature {
    private CreatureSprite sprite;
    private String name;

    public CreatureSprite getSprite() {
        return sprite;
    }

    public String getName() {
        return name;
    }

    public Creature(String spriteFileName, String name) {
        this.sprite = new CreatureSprite(spriteFileName);
        this.name = name;
    }

}
