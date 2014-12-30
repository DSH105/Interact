package com.dsh105.interact.api;

import java.util.Map;

public interface BuilderFactory {

    Inventory.Builder createInventoryBuilder();

    Position.Builder createPositionBuilder();

    Position.Builder createPositionBuilder(Position position);

    Icon.Builder createIconBuilder();

    Layout createLayout(Position[] positions);

    Layout deserializeLayout(Map<String, Object> args);
}