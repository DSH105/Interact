package com.dsh105.interact.api;

import com.dsh105.commodus.container.PlayerContainer;

public interface ClickAction extends Action {

    void onClick(Inventory inventory, PlayerContainer player, Position position);

    abstract class Close implements ClickAction {

        @Override
        public void onClick(Inventory inventory, PlayerContainer player, Position position) {
            inventory.close(player);
        }
    }
}