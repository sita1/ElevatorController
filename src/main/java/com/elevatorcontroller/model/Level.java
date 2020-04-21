package com.elevatorcontroller.model;

import java.util.HashMap;
import java.util.Map;

public enum Level {
    ABOUT(1),
    CODING(2),
    DATABASES(3);

    private int value;
    private static Map map = new HashMap<>();

    private Level(int value) {
        this.value = value;
    }

    static {
        for (Level pageType : Level.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static Level valueOf(int pageType) {
        return (Level) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}