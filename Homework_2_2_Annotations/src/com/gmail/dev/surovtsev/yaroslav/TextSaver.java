package com.gmail.dev.surovtsev.yaroslav;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TextSaver {
    private TextContainer textContainer;

    public TextSaver() {
        super();
    }

    public TextSaver(TextContainer textContainer) {
        super();
        this.textContainer = textContainer;
    }

    public TextContainer getTextContainer() {
        return textContainer;
    }

    public void setTextContainer(TextContainer textContainer) {
        this.textContainer = textContainer;
    }

    public void save() {
        Class<?> cls = textContainer.getClass();
        Method[] methods = cls.getDeclaredMethods();
        for (Method method: methods) {
            if (method.isAnnotationPresent(Saver.class)) {
                try {
                    method.invoke(textContainer);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String toString() {
        return "TextSaver{" +
                "textContainer=" + textContainer +
                '}';
    }
}
