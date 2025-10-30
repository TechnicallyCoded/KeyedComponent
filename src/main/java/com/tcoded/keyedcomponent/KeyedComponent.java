package com.tcoded.keyedcomponent;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.Keyed;
import net.kyori.adventure.text.ComponentLike;
import org.jetbrains.annotations.NotNull;

public interface KeyedComponent extends ComponentLike, Keyed {

    @NotNull Key key();

    ComponentLike value();

}
