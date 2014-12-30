package com.dsh105.interact;

import com.dsh105.commodus.bukkit.ItemRegistry;
import com.dsh105.commodus.container.ItemStackContainer;
import com.dsh105.commodus.sponge.SpongeUtil;
import com.dsh105.interact.api.Icon;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.api.item.ItemType;

import java.util.Collections;
import java.util.List;

public class InteractIcon implements Icon {

    private String typeId;
    private String name;
    private List<String> lore;
    private int quantity;

    protected InteractIcon(String typeId, String name, List<String> lore, int quantity) {
        this.typeId = typeId;
        this.name = name;
        this.lore = lore;
        this.quantity = quantity;
    }

    @Override
    public Material getBukkitType() {
        return Material.getMaterial(ItemRegistry.getId(typeId).getId());
    }

    @Override
    public ItemType getSpongeType() {
        return SpongeUtil.getGame().getRegistry().getItem(typeId).get();
    }

    @Override
    public String getTypeId() {
        return typeId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getLore() {
        return Collections.unmodifiableList(lore);
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public ItemStackContainer getStack() {
        return ItemStackContainer.of(typeId, quantity);
    }

    @Override
    public ItemStack asBukkit() {
        return getStack().asBukkit();
    }

    @Override
    public org.spongepowered.api.item.inventory.ItemStack asSponge() {
        return getStack().asSponge();
    }

    @Override
    public Builder builder() {
        return new InteractIconBuilder().of(getStack());
    }
}