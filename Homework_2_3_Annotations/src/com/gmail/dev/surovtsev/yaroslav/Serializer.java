package com.gmail.dev.surovtsev.yaroslav;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@interface Save {
}

public class Serializer {

    public static String serialize(Object o) throws IllegalAccessException {
        Class<?> cls = o.getClass();
        StringBuilder sb = new StringBuilder();

        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Save.class)) {
                continue;
            }
            sb.append(field.getName()).append(";");
        }
        sb.append("\n");

        for (Field field : fields) {
            if (!field.isAnnotationPresent(Save.class)) {
                continue;
            }
            if (Modifier.isPrivate(field.getModifiers())) {
                field.setAccessible(true);
            }

            if (field.getType() == int.class) {
                sb.append(field.getInt(o));
            } else if (field.getType() == String.class) {
                sb.append((String) field.get(o));
            } else if (field.getType() == double.class) {
                sb.append(field.getInt(o));
            }

            sb.append(";");
        }
        return sb.toString();
    }

    public static <T> T deserialize(String header, String s, Class<T> cls) throws InvalidParameterException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException,
            NoSuchFieldException {
        String[] fieldsNames = header.split(";");
        String[] fieldsValues = s.split(";");
        Field[] allFieldsCls = cls.getDeclaredFields();
        List<Field> fieldsCls = new ArrayList<>();
        for (Field field : allFieldsCls) {
            if (field.isAnnotationPresent(Save.class)) {
                fieldsCls.add(field);
            }
        }
        if (fieldsNames.length != fieldsCls.size()) {
            throw new InvalidParameterException();
        }
        T obj = (T) cls.getDeclaredConstructor().newInstance();
        for (int i = 0; i < fieldsNames.length; i++) {
            String fieldName = fieldsNames[i];
            Field clsField = cls.getDeclaredField(fieldName);

            if (!clsField.isAnnotationPresent(Save.class)) {
                continue;
            }
            if (Modifier.isPrivate(clsField.getModifiers())) {
                clsField.setAccessible(true);
            }

            if (clsField.getType() == int.class) {
                clsField.setInt(obj, Integer.parseInt(fieldsValues[i]));
            } else if (clsField.getType() == String.class) {
                clsField.set(obj, fieldsValues[i]);
            } else if (clsField.getType() == double.class) {
                clsField.setDouble(obj, Double.parseDouble(fieldsValues[i]));
            }
        }

        return obj;
    }
}
