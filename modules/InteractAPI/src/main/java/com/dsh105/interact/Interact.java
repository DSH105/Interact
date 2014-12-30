/*
 * This file is part of Interact.
 *
 * Interact is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Interact is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Interact.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.dsh105.interact;

import com.dsh105.commodus.Affirm;
import com.dsh105.commodus.container.ItemStackContainer;
import com.dsh105.interact.api.*;

import java.util.Map;

public class Interact {

    protected static Icon EMPTY_SLOT;
    private static InteractPlugin PLUGIN;
    private static BuilderFactory FACTORY;

    static {
        try {
            FACTORY = (BuilderFactory) Class.forName(BuilderFactory.class.getCanonicalName().replace("Builder", "InteractBuilder")).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new IllegalStateException("Interact factory is not available; plugin will not be able to function properly.");
        }
    }

    protected static void prepare(InteractPlugin plugin) {
        if (PLUGIN != null) {
            throw new IllegalStateException("Plugin can only be instantiated once!");
        }
        PLUGIN = plugin;
        setEmptySlotIcon(icon().of(ItemStackContainer.of("air", 1)).build());
    }

    protected static void disable() {
        PLUGIN = null;
    }

    protected static InteractPlugin getPlugin() {
        return PLUGIN;
    }

    public static Icon getEmptySlotIcon() {
        return EMPTY_SLOT;
    }

    public static void setEmptySlotIcon(Icon icon) {
        Affirm.notNull(icon);
        EMPTY_SLOT = icon;
    }

    public static Inventory.Builder inventory() {
        return FACTORY.createInventoryBuilder();
    }

    public static Inventory.Builder builder(Inventory inventory) {
        Inventory.Builder builder = inventory()
                .name(inventory.getName())
                .interactIcon(inventory.getInteractIcon())
                .onClick(inventory.getClickAction())
                .onOpen(inventory.getOpenAction());
        for (Map.Entry<Integer, Icon> entry : inventory.getLayout().getIcons().entrySet()) {
            builder.at(position().slot(entry.getKey()).icon(entry.getValue()).build());
        }
        return builder;
    }

    public static Position.Builder position() {
        return FACTORY.createPositionBuilder();
    }

    public static Position.Builder position(Position position) {
        return FACTORY.createPositionBuilder(position);
    }

    public static Icon.Builder icon() {
        return FACTORY.createIconBuilder();
    }

    public static Icon.Builder icon(Icon icon) {
        return icon.builder();
    }

    public static Layout layout(Position[] positions) {
        return FACTORY.createLayoutBuilder(positions);
    }
}