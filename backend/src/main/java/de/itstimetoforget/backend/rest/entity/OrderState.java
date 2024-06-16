package de.itstimetoforget.backend.rest.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum OrderState {
    OPEN("offen"),
    IN_PROGRESS("in bearbeitung"),
    CALL_BACK("rückruf"),
    FOLLOW_UP("zur wiedervorlage"),
    WAIT_FOR_ORDERED_PARTS("wartet auf bestellte Teile"),
    COMPLETED("Erledigt"),
    ;


    private final String name;

    OrderState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @JsonCreator
    public static OrderState getState(String string) {
        try {
            OrderState.valueOf(string);
        } catch (IllegalArgumentException ignore) {
            ;
        }
        for (OrderState state : values()) {
            if (state.name.equals(string)) {
                return state;
            }
        }
        throw new IllegalArgumentException("No enum constant " + string);
    }
}
