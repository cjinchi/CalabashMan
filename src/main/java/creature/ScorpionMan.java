package creature;

public class ScorpionMan extends Creature {
     private ScorpionMan(String spriteFileName, String name) {
        super(spriteFileName, name);
    }

    private static ScorpionMan sm;
     public static ScorpionMan getInstance(){
         if(sm==null){
             sm = new ScorpionMan("scorpion.png","蝎子精");
         }
         return sm;
     }

}
