
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        ArrayList<SuperClass> list = new ArrayList<>();
        list.add(new SuperClass());
        list.add(new class1());
        list.add(new class2());

        System.out.println(list);
        System.out.println(list.stream().map(SuperClass::getClass).collect(Collectors.toList()));
        System.out.println(list.stream().map(superClass -> superClass.getClass().equals(class1.class)).collect(Collectors.toList()));
        System.out.println(list.stream().filter(superClass -> superClass.getClass().equals(class1.class)).collect(Collectors.toList()));
        System.out.println(list.stream().filter(superClass -> Objects.equals(superClass.getClass(), class1.class)).map(superClass -> (class1) superClass).collect(Collectors.toList()));
    }
}
