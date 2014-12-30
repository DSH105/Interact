package com.dsh105.interact;

import com.dsh105.interact.api.Icon;
import com.dsh105.interact.api.Position;

public class InteractPosition implements Position {

    private Icon icon;
    private int slot;

    protected InteractPosition(Icon icon, int slot) {
        this.icon = icon;
        this.slot = slot;
    }

    @Override
    public Icon getIcon() {
        return icon;
    }

    @Override
    public int getSlot() {
        return slot;
    }

    @Override
    public Position.Builder builder() {
        return Interact.position(this);
    }
}