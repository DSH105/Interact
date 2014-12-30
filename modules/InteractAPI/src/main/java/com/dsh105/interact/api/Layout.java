package com.dsh105.interact.api;

import java.util.SortedMap;

public interface Layout {

    SortedMap<Integer, Icon> getIcons();

    int getMaximumSize();

    void adjustMaximumSize(int size);

    void remove(int slot);

    void set(int slot, Icon icon);

    Icon get(int slot);

    void move(int slot, int destination);

    void swap(int slot, int with);
}