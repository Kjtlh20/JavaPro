package com.gmail.dev.surovtsev.yaroslav;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.annotation.*;

@SaveTo(path = "files/file.txt")
public class TextContainer {
    private String text;

    public TextContainer() {
        super();
    }

    public TextContainer(String text) {
        super();
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Saver
    public void save() {
        final Class<?> cls = TextContainer.class;
        if (cls.isAnnotationPresent(SaveTo.class)) {
            SaveTo an = cls.getAnnotation(SaveTo.class);
            String path = an.path();
            File file = new File(path);
            try (PrintWriter pw = new PrintWriter(file)) {
                pw.println(text);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "TextContainer{" +
                "text='" + text + '\'' +
                '}';
    }
}

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@interface SaveTo {
    String path();
}

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@interface Saver {
}