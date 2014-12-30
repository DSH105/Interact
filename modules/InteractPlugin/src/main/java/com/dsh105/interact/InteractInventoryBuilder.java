package com.dsh105.interact;

import com.dsh105.commodus.Affirm;
import com.dsh105.interact.api.*;

public abstract class InteractInventoryBuilder implements Inventory.Builder {

    protected String name;
    protected int maximumSize;
    protected Position[] positions;
    protected Icon interactIcon;
    protected ClickAction clickAction;
    protected OpenAction openAction;

    @Override
    public Inventory.Builder name(String name) {
        Affirm.notNull(name);
        this.name = name;
        return this;
    }

    @Override
    public Inventory.Builder at(Position position) {
        if (maximumSize <= 0) {
            throw new IllegalStateException("Inventory size has not yet been defined (please use \"#size(int)\")");
        }
        Affirm.isTrue(position.getSlot() >= maximumSize, String.format(InteractLayout.ILLEGAL_POSITION, position.getSlot(), maximumSize));
        this.positions[position.getSlot()] = position;
        return this;
    }

    @Override
    public Inventory.Builder at(Position.Builder position) {
        return at(position.build());
    }

    @Override
    public Inventory.Builder interactIcon(Icon icon) {
        Affirm.notNull(icon);
        this.interactIcon = icon;
        return this;
    }

    @Override
    public Inventory.Builder interactIcon(Icon.Builder icon) {
        Affirm.notNull(icon);
        return interactIcon(icon.build());
    }

    @Override
    public Inventory.Builder onClick(ClickAction action) {
        this.clickAction = action;
        return this;
    }

    @Override
    public Inventory.Builder onOpen(OpenAction action) {
        this.openAction = action;
        return this;
    }

    public Inventory.Builder size(int size) {
        Affirm.isTrue(size > 0, String.format("Inventory size must be greater than 0 (given: %s)", size));
        this.maximumSize = InteractLayout.adjustSize(size, 9);
        positions = new Position[maximumSize];
        for (int i = 0; i < maximumSize; i++) {
            at(Interact.position().slot(i).icon(Interact.getEmptySlotIcon()).build());
        }
        return this;
    }
}