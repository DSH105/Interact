package com.dsh105.interact;

import com.dsh105.interact.api.Icon;
import com.dsh105.interact.api.Position;

public class InteractPositionBuilder implements Position.Builder {

    private int slot;
    private Icon icon;

    @Override
    public Position.Builder slot(int slot) {
        this.slot = slot;
        return this;
    }

    @Override
    public Position.Builder icon(Icon icon) {
        this.icon = icon;
        return this;
    }

    @Override
    public Position.Builder icon(Icon.Builder icon) {
        return icon(icon.build());
    }

    @Override
    public Position build() {
        return new InteractPosition(icon, slot);
    }
}