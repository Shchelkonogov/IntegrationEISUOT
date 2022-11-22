package ru.tecon.integrationEISUOT.util.gson;

import com.google.gson.FieldNamingStrategy;

import java.lang.reflect.Field;
import java.nio.CharBuffer;
import java.util.Objects;

/**
 * Моя реализация стратегии разбора имен для gson.<br>
 * Между маленькой буквой и большой буквой ставится разделитель.<br>
 * Между маленькой буквой и цифрой ставится разделитель.<br>
 * Между цифой и боьшой буквой ставится разделитель.<br>
 * Если имя поля класса содержит символ '$', то его заменяю на резделитель.
 * @author Maksim Shchelkonogov
 */
abstract class GsonFieldNamingStrategy implements FieldNamingStrategy {

    @Override
    public final String translateName(Field f) {
        Objects.requireNonNull(f);
        String propertyName = f.getName();
        CharBuffer charBuffer = CharBuffer.allocate(propertyName.length() * 2);
        char last = 0;

        for(int i = 0; i < propertyName.length(); i++) {
            char current = propertyName.charAt(i);
            if (i > 0 && (Character.isUpperCase(current) || Character.isDigit(current)) && this.isLowerCaseCharacter(last)) {
                charBuffer.append(getSeparator());
            } else {
                if (i > 0 && Character.isUpperCase(current) && Character.isDigit(last)) {
                    charBuffer.append(getSeparator());
                }
            }

            if (current == '$') {
                charBuffer.append(getSeparator());
            } else {
                charBuffer.append(Character.toLowerCase(current));
            }
            last = current;
        }

        return new String(charBuffer.array(), 0, charBuffer.position());
    }

    private boolean isLowerCaseCharacter(char character) {
        return Character.isAlphabetic(character) && Character.isLowerCase(character);
    }

    protected abstract char getSeparator();
}
