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

import com.dsh105.commodus.ServerUtil;
import com.dsh105.interact.api.*;

import java.util.SortedMap;
import java.util.TreeMap;

public class InteractBuilderFactory implements BuilderFactory {

    @Override
    public Inventory.Builder createInventoryBuilder() {
        switch (ServerUtil.getServerBrand().getCapsule()) {
            case BUKKIT:
                return new BukkitInteractInventoryBuilder();
            case SPONGE:
                // TODO:
            default:
                throw new IllegalStateException("Failed to create Inventory.Builder: Unknown server brand.");
        }
    }

    @Override
    public Position.Builder createPositionBuilder() {
        return new InteractPositionBuilder();
    }

    @Override
    public Position.Builder createPositionBuilder(Position position) {
        return new InteractPositionBuilder().slot(position.getSlot()).icon(position.getIcon());
    }

    @Override
    public Icon.Builder createIconBuilder() {
        return new InteractIconBuilder();
    }

    @Override
    public Layout createLayoutBuilder(Position[] positions) {
        SortedMap<Integer, Icon> icons = new TreeMap<>();
        for (Position position : positions) {
            icons.put(position.getSlot(), position.getIcon());
        }
        return new InteractLayout(icons);
    }
}