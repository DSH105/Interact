package com.dsh105.interact;

import com.dsh105.interact.api.Icon;
import com.dsh105.interact.api.Position;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("slot", slot);
        map.put("icon", icon.serialize());
        return map;
    }
}