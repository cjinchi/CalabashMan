package app;

import com.sun.jmx.snmp.SnmpUsmKeyHandler;
import creature.*;
import field.BattleField;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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

//        List<CalabashMan> brothers = new ArrayList<>();
        for(int i=1;i<=7;i++){
            CalabashMan man = CalabashMan.getInstance(i);
//            brothers.add(man);
            root.getChildren().addAll(man.getSprite().getProfileImage(),man.getSprite().getHpBar());
            man.enterBattleField(bf,1,i+1);
        }

        GrandFather grandFather = GrandFather.getInstance();
        root.getChildren().addAll(grandFather.getSprite().getProfileImage(),grandFather.getSprite().getHpBar());
        grandFather.enterBattleField(bf,0,HEIGHT_UNIT/2);

        SnakeWoman snakeWoman = SnakeWoman.getInstance();
        root.getChildren().addAll(snakeWoman.getSprite().getProfileImage(), snakeWoman.getSprite().getHpBar());
        snakeWoman.enterBattleField(bf,WIDTH_UNIT-1,HEIGHT_UNIT/2);

        ScorpionMan scorpionMan = ScorpionMan.getInstance();
        root.getChildren().addAll(scorpionMan.getSprite().getProfileImage(),scorpionMan.getSprite().getHpBar());
        scorpionMan.enterBattleField(bf,WIDTH_UNIT-4,HEIGHT_UNIT/2);
        Thread smTread = new Thread(scorpionMan);
        smTread.setName(scorpionMan.getName());
        smTread.start();

        List<Minion> minions = new ArrayList<>();
        for(int i=1;i<=Minion.TOTAL_NUM;i++){
            Minion minion = Minion.getInstance(i);
            minions.add(minion);
            root.getChildren().addAll(minion.getSprite().getProfileImage(),minion.getSprite().getHpBar());
            minion.enterBattleField(bf,WIDTH_UNIT-(i%3+1),(i<=3?-1:1)*(3-i%3)+HEIGHT_UNIT/2);

            Thread thread = new Thread(minion);
            thread.setName(minion.getName());
            thread.start();
        }

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                bf.clickOn(((int)event.getX())/UNIT_LENGTH,((int)event.getY())/UNIT_LENGTH);

            }
        });

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Creature creature = bf.getCurrentSelectCreature();
                if(creature!=null){
                    switch (event.getCode()){
                        case W:creature.moveUp();break;
                        case S:creature.moveDown();break;
                        case A:creature.moveLeft();break;
                        case D:creature.moveRight();break;
                    }
                }

            }
        });



        primaryStage.show();
    }
}
