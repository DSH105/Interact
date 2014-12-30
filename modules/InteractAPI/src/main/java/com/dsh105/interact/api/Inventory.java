package com.dsh105.interact.api;

import com.dsh105.commodus.container.PlayerContainer;

import java.util.UUID;

public interface Inventory {

    UUID getId();

    String getName();

    Layout getLayout();

    Icon getInteractIcon();

    ClickAction getClickAction();

    OpenAction getOpenAction();

    void show(PlayerContainer player);

    void close(PlayerContainer player);

    Inventory.Builder builder();

    interface Builder extends InteractBuilder {

        Inventory.Builder name(String name);

        Inventory.Builder at(Position position);

        Inventory.Builder at(Position.Builder position);

        Inventory.Builder interactIcon(Icon icon);

        Inventory.Builder interactIcon(Icon.Builder icon);

        Inventory.Builder onClick(ClickAction action);

        Inventory.Builder onOpen(OpenAction action);

        Inventory build();
    }
}