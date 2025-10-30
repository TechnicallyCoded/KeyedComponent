package com.tcoded.keyedcomponent;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"unused", "ClassCanBeRecord"})
public class KeyedComponentImpl implements KeyedComponent {

    public static KeyedComponent of(Key key, ComponentLike value) {
        return new KeyedComponentImpl(key, value);
    }

    private final Key key;
    private final ComponentLike value;

    private KeyedComponentImpl(Key key, ComponentLike value) {
        this.key = key;
        this.value = value;
    }

    @NotNull
    @Override
    public Key key() {
        return key;
    }

    @Override
    public ComponentLike value() {
        return value;
    }

    @Override
    public @NotNull Component asComponent() {
        return value.asComponent();
    }

}
