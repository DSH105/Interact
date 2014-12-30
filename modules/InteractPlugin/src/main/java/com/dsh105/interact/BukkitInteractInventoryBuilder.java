package com.dsh105.interact;

import com.dsh105.interact.api.Inventory;

public class BukkitInteractInventoryBuilder extends InteractInventoryBuilder {

    @Override
    public Inventory build() {
        return new BukkitInteractInventory(name, Interact.layout(positions), interactIcon, clickAction, openAction);
    }
}