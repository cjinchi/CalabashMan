package creature;

import javafx.embed.swing.JFXPanel;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GrandFatherTest {
    @Test
    public void grandFatherGetInstance(){
        //disable error "Internal graphics not initialized yet"
        JFXPanel jfxPanel = new JFXPanel();

        GrandFather base = GrandFather.getInstance();
        for(int i=0;i<5;i++){
            new Thread(()->{
                GrandFather temp = GrandFather.getInstance();
                assertTrue(temp == base);
            }).start();
        }
    }
}
