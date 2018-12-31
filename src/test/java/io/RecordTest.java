package io;

import control.Action;
import control.MouseAction;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecordTest {
    @Test
    void readTest(){
        File file = new File("record.xml");
        List<Action> actions = RecordReader.read(file);
        assertFalse(actions.isEmpty());

        for(Action action:actions){
            assertTrue(action.getTime()>0);
            String type = action.getType();
            assertTrue(type.equals("MOUSE")||type.equals("KEYBOARD")||type.equals("SKILL"));
        }
    }



}
