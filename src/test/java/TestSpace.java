
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestSpace {

    @Test
    public void test() {
        File levelFile = new File("src/main/resources/levels/level_1");
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(levelFile));
            String line = fileReader.readLine();
            ArrayList<String> list = new ArrayList<>(List.of(line.split(";")));
            System.out.println(list);
            System.out.println(list.size());
            System.out.println(list.get(0));
            System.out.println(list.get(1));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
