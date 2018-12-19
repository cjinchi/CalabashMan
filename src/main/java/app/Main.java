package app;

import com.sun.jmx.snmp.SnmpUsmKeyHandler;
import creature.*;
import field.BattleField;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sun.tools.jconsole.Plotter;
import ui.BattleFieldSprite;
import ui.CreatureSprite;
import ui.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public static final int WIDTH_UNIT = 13;
    public static final int HEIGHT_UNIT = 11;
    public static final int UNIT_LENGTH = 80;

    private Creature[][] creatures = new Creature[WIDTH_UNIT][HEIGHT_UNIT];


    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("CalabashMan VS SnakeWoman (and others)");

        Group root = new Group();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../hp.css").toExternalForm());
        primaryStage.setScene(scene);

        Canvas canvas = new Canvas(WIDTH_UNIT*UNIT_LENGTH,HEIGHT_UNIT*UNIT_LENGTH);
        root.getChildren().add(canvas);

        BattleField bf = new BattleField(WIDTH_UNIT,HEIGHT_UNIT);
        bf.getBfs().draw(UNIT_LENGTH,canvas);

        List<CalabashMan> brothers = new ArrayList<>();
        for(int i=1;i<=7;i++){
            CalabashMan man = CalabashMan.getInstance(i);
            brothers.add(man);
            root.getChildren().addAll(man.getSprite().getProfileImage(),man.getSprite().getHpBar());
//            man.moveTo(1,i+1);
            man.enterBattleField(bf,1,i+1);
        }

        GrandFather grandFather = GrandFather.getInstance();
        root.getChildren().addAll(grandFather.getSprite().getProfileImage(),grandFather.getSprite().getHpBar());
//        grandFather.moveTo(0,HEIGHT_UNIT/2);
        grandFather.enterBattleField(bf,0,HEIGHT_UNIT/2);

        SnakeWoman snakeWoman = SnakeWoman.getInstance();
        root.getChildren().addAll(snakeWoman.getSprite().getProfileImage(), snakeWoman.getSprite().getHpBar());
//        snakeWoman.moveTo(WIDTH_UNIT-1,HEIGHT_UNIT/2);
        snakeWoman.enterBattleField(bf,WIDTH_UNIT-1,HEIGHT_UNIT/2);

        ScorpionMan scorpionMan = ScorpionMan.getInstance();
        root.getChildren().addAll(scorpionMan.getSprite().getProfileImage(),scorpionMan.getSprite().getHpBar());
//        scorpionMan.moveTo(WIDTH_UNIT-4,HEIGHT_UNIT/2);
        scorpionMan.enterBattleField(bf,WIDTH_UNIT-4,HEIGHT_UNIT/2);

        List<Minion> minions = new ArrayList<>();
        for(int i=1;i<=Minion.TOTAL_NUM;i++){
            Minion minion = Minion.getInstance(i);
            minions.add(minion);
            root.getChildren().addAll(minion.getSprite().getProfileImage(),minion.getSprite().getHpBar());
//            minion.moveTo(WIDTH_UNIT-(i%3+1),(i<=3?-1:1)*(3-i%3)+HEIGHT_UNIT/2);
            minion.enterBattleField(bf,WIDTH_UNIT-(i%3+1),(i<=3?-1:1)*(3-i%3)+HEIGHT_UNIT/2);
        }

        primaryStage.show();
    }
}
