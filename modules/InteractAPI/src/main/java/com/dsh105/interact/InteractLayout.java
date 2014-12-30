package com.dsh105.interact;

import com.dsh105.commodus.Affirm;
import com.dsh105.interact.api.Icon;
import com.dsh105.interact.api.Layout;

import java.util.*;

public class InteractLayout implements Layout {

    protected static final String ILLEGAL_POSITION = "Provided slot (%s) is not within the inventory size range (%s).";

    private final SortedMap<Integer, Icon> icons;
    private int maximumSize;

    protected InteractLayout(SortedMap<Integer, Icon> icons) {
        this.icons = icons;
        maximumSize = adjustSize(icons.size(), 9);
    }

    protected static int adjustSize(int size, int rowLength) {
        if (size < 0) {
            size = 9;
        } else if (size % rowLength != 0) {
            size += rowLength - (size % rowLength);
        }
        return size;
    }

    @Override
    public SortedMap<Integer, Icon> getIcons() {
        return Collections.unmodifiableSortedMap(icons);
    }

    @Override
    public int getMaximumSize() {
        return maximumSize;
    }

    @Override
    public void adjustMaximumSize(int size) {
        size = adjustSize(size, 9);
        if (size > maximumSize) {
            for (int i = maximumSize; i < size; i++) {
                set(i, Interact.getEmptySlotIcon());
            }
        } else {
            for (int i = maximumSize - 1; i >= size; i--) {
                remove(i);
            }
        }
    }

    @Override
    public void remove(int slot) {
        Affirm.isTrue(slot <= maximumSize, String.format(ILLEGAL_POSITION, slot, maximumSize));
        set(slot, Interact.getEmptySlotIcon());
    }

    @Override
    public void set(int slot, Icon icon) {
        Affirm.isTrue(slot <= maximumSize, String.format(ILLEGAL_POSITION, slot, maximumSize));
        icons.put(slot, icon);
    }

    @Override
    public Icon get(int slot) {
        Affirm.isTrue(slot <= maximumSize, String.format(ILLEGAL_POSITION, slot, maximumSize));
        return icons.get(slot);
    }

    @Override
    public void move(int slot, int destination) {
        Affirm.isTrue(slot <= maximumSize, String.format(ILLEGAL_POSITION, slot, maximumSize));
        Affirm.isTrue(destination <= maximumSize, String.format(ILLEGAL_POSITION, destination, maximumSize));
        set(destination, get(slot));
        set(slot, Interact.getEmptySlotIcon());
    }

    @Override
    public void swap(int slot, int with) {
        Affirm.isTrue(slot <= maximumSize, String.format(ILLEGAL_POSITION, slot, maximumSize));
        Affirm.isTrue(with <= maximumSize, String.format(ILLEGAL_POSITION, with, maximumSize));
        Icon icon = get(slot);
        set(slot, get(with));
        set(with, icon);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("size", maximumSize);
        for (int i = 0; i <= maximumSize; i++) {
            Icon icon = get(i);
            if (!icon.equals(Interact.getEmptySlotIcon())){
                map.put("slots.slot-" + i, icon.serialize());
            }
        }
        return map;
    }

    public static Layout deserialize(Map<String, Object> args) {
        return Interact.layout(args);
    }
}