package ru.tecon.integrationEISUOT.service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Maksim Shchelkonogov
 */
@ApplicationPath("/")
public class MyApplication extends Application {

    @Override
    public Map<String, Object> getProperties() {
        // Отключил разбор json с помощью jersey в пользу gson (решил изучить эту библиотеку)
        Map<String, Object> props = new HashMap<>();
        props.put("jersey.config.server.disableMoxyJson", true);
        return props;
    }
}
