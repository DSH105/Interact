package com.dsh105.interact.api;

public interface BuilderFactory {

    Inventory.Builder createInventoryBuilder();

    Position.Builder createPositionBuilder();

    Position.Builder createPositionBuilder(Position position);

    Icon.Builder createIconBuilder();

    Layout createLayoutBuilder(Position[] positions);
}