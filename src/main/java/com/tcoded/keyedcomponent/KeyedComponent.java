package com.tcoded.keyedcomponent;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.Keyed;
import net.kyori.adventure.text.ComponentLike;
import org.jetbrains.annotations.NotNull;

public interface KeyedComponent extends ComponentLike, Keyed {

    @SuppressWarnings("PatternValidation")
    static KeyedComponent of(@NotNull String key, ComponentLike value) {
        return KeyedComponentImpl.create(Key.key("", key), value);
    }

    static KeyedComponent of(@NotNull Key key, ComponentLike value) {
        return KeyedComponentImpl.create(key, value);
    }

    @NotNull Key key();

    ComponentLike value();

}
