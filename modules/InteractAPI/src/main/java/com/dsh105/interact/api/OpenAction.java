package com.dsh105.interact.api;

import com.dsh105.commodus.container.PlayerContainer;

public interface OpenAction {

    void onOpen(Inventory inventory, PlayerContainer player);
}