/* 3**. Написать код, который сериализирует и десериализирует в/из файла
 * все значения полей класса, которые отмечены аннотацией @Save.
 */
package com.gmail.dev.surovtsev.yaroslav;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Cat cat = new Cat("cat1", 6, 7.8);
        File file = new File("file.csv");
        try (PrintWriter pw = new PrintWriter(file);) {
            pw.println(Serializer.serialize(cat));
        } catch (FileNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            Stream<String> stream = Files.lines(Paths.get("file.csv"));
            List<String> list = stream.collect(Collectors.toList());
            Cat newCat = (Cat) Serializer.deserialize(list.get(0), list.get(1), Cat.class);
            System.out.println(newCat);
        } catch (IOException | InstantiationException | InvocationTargetException | NoSuchMethodException
                | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
