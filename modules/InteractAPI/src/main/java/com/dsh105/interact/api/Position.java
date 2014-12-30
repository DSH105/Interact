package com.dsh105.interact.api;

public interface Position {

    Icon getIcon();

    int getSlot();

    Position.Builder builder();

    interface Builder extends InteractBuilder {

        Builder slot(int slot);

        Position.Builder icon(Icon icon);

        Position.Builder icon(Icon.Builder icon);

        Position build();
    }
}