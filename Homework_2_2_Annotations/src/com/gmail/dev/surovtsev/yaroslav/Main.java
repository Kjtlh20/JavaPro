/* 2*. Написать класс TextContainer, который содержит в себе строку.
 * С помощью механизма аннотаций указать 1) в какой файл должен сохраниться текст 2) метод,
 * который выполнит сохранение. Написать класс Saver, который сохранит
 * поле класса TextContainer в указанный файл.
 * @SaveTo(path=“c:\\file.txt”) class Container {
 * String text = “...”;
 * @Saver
 * public void save(..) {...}
 * }
 */
package com.gmail.dev.surovtsev.yaroslav;

public class Main {

    public static void main(String[] args) {
        TextContainer textContainer = new TextContainer("Hello world");
        TextSaver textSaver = new TextSaver(textContainer);
        textSaver.save();
    }
}
