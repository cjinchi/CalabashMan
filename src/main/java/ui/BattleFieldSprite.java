package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class BattleFieldSprite {
    private List<List<Image>> fields;

    private Image darkGrass = ImageLoader.getImage("DarkGrass");
    private Image lightGrass = ImageLoader.getImage("LightGrass");

    public BattleFieldSprite(int width,int height){
        fields = new ArrayList<>();
        for(int i=0;i<width;i++){
            List<Image> row = new ArrayList<>();
            for(int j=0;j<height;j++){
                row.add((i+j)%2==0?darkGrass:lightGrass);
            }
            fields.add(row);
        }
    }

    public void draw(int length, Canvas canvas){
        for(int i=0;i<fields.size();i++){
            List<Image> row = fields.get(i);
            for(int j=0;j<row.size();j++){
                canvas.getGraphicsContext2D().drawImage(row.get(j),i*length,j*length,length,length);
            }
        }
    }

}
