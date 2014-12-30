package com.dsh105.interact.api;

import com.dsh105.commodus.container.PlayerContainer;

public interface ClickAction {

    void onClick(Inventory inventory, PlayerContainer player, Position position);

    class Close implements ClickAction {

        @Override
        public void onClick(Inventory inventory, PlayerContainer player, Position position) {
            inventory.close(player);
        }
    }
}