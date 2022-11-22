package ru.tecon.integrationEISUOT.util.gson;

import com.google.gson.*;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gson deserializer, который проверяет наличие {@link JsonRequired}
 * и если разобранное поле с этой аннотацией будет null, то вернет ошибку {@link JsonParseException}.
 * Так же можно добавить свою собственную реализацию {@link FieldNamingStrategy}
 * @author Maksim Shchelkonogov
 */
public final class AnnotatedDeserializer<T> implements JsonDeserializer<T> {

    private static Logger logger = Logger.getLogger(AnnotatedDeserializer.class.getName());

    private FieldNamingStrategy strategy;

    private AnnotatedDeserializer() {
    }

    /**
     * @param strategy устанавливает свою собственную стратегию разбора имен
     */
    public AnnotatedDeserializer(FieldNamingStrategy strategy) {
        this();
        this.strategy = strategy;
    }

    private Gson getGson() {
        if (strategy == null) {
            return new Gson();
        } else {
            return new GsonBuilder()
                    .setFieldNamingStrategy(strategy)
                    .create();
        }
    }

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        T pojo = getGson().fromJson(json, typeOfT);

        Field[] fields = pojo.getClass().getDeclaredFields();
        for (Field f: fields) {
            if ((pojo.getClass().getAnnotation(JsonRequired.class) != null) || (f.getAnnotation(JsonRequired.class) != null)) {
                try {
                    f.setAccessible(true);
                    if (f.get(pojo) == null) {
                        throw new JsonParseException("Missing field in JSON: " +
                                (strategy == null ? f.getName() : strategy.translateName(f)));
                    }
                } catch (IllegalAccessException e) {
                    logger.log(Level.SEVERE, "deserialize error", e);
                }
            }
        }
        return pojo;
    }
}
