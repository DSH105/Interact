package com.dsh105.interact;

import com.dsh105.commodus.Affirm;
import com.dsh105.commodus.bukkit.ItemRegistry;
import com.dsh105.commodus.container.ItemStackContainer;
import com.dsh105.interact.api.Icon;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.api.item.ItemType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class InteractIconBuilder implements Icon.Builder {

    private String name = "";
    private String[] lore = new String[0];
    private String typeId;
    private int quantity = 1;

    @Override
    public Icon.Builder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Icon.Builder lore(String... lore) {
        if (lore == null || lore.length <= 0) {
            this.lore = new String[0];
        }
        this.lore = lore;
        return this;
    }

    @Override
    public Icon.Builder type(Material bukkitType) {
        return type(ItemRegistry.getName(bukkitType));
    }

    @Override
    public Icon.Builder type(ItemType spongeType) {
        return type(spongeType.getId());
    }

    @Override
    public Icon.Builder quantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public Icon.Builder of(ItemStack bukkitItemStack) {
        return of(ItemStackContainer.of(bukkitItemStack));
    }

    @Override
    public Icon.Builder of(org.spongepowered.api.item.inventory.ItemStack spongeItemStack) {
        return of(ItemStackContainer.of(spongeItemStack));
    }

    @Override
    public Icon.Builder of(ItemStackContainer itemStack) {
        return type(itemStack.getId()).quantity(itemStack.getQuantity());
    }

    @Override
    public Icon.Builder reset() {
        return type((String) null).lore().quantity(0);
    }

    @Override
    public Icon build() {
        Affirm.notNull(name);
        Affirm.notNull(typeId);
        Affirm.isTrue(quantity >= 0);
        return new InteractIcon(typeId, name, Arrays.asList(lore), quantity);
    }

    protected Icon.Builder type(String typeId) {
        this.typeId = typeId;
        return this;
    }

    @Override
    public Icon.Builder from(Map<String, Object> args) {
        if (args.containsKey("typeId")) {
            typeId = (String) args.get("typeId");
        }
        if (args.containsKey("name")) {
            name((String) args.get("name"));
        }
        if (args.containsKey("lore")) {
            lore(((List<String>) args.get("lore")).toArray(new String[0]));
        }
        if (args.containsKey("quantity")) {
            quantity((int) args.get("quantity"));
        }
        return this;
    }
}