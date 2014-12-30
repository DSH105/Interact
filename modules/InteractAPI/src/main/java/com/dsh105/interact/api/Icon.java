package com.dsh105.interact.api;

import com.dsh105.commodus.container.ItemStackContainer;

import java.util.List;

public interface Icon extends Serializable {

    org.bukkit.Material getBukkitType();

    org.spongepowered.api.item.ItemType getSpongeType();

    String getTypeId();

    String getName();

    List<String> getLore();

    int getQuantity();

    ItemStackContainer getStack();

    org.bukkit.inventory.ItemStack asBukkit();

    org.spongepowered.api.item.inventory.ItemStack asSponge();

    Icon.Builder builder();

    interface Builder extends InteractBuilder<Icon.Builder> {

        Icon.Builder name(String name);

        Icon.Builder lore(String... lore);

        Icon.Builder type(org.bukkit.Material bukkitType);

        Icon.Builder type(org.spongepowered.api.item.ItemType spongeType);

        Icon.Builder quantity(int quantity);

        Icon.Builder of(org.bukkit.inventory.ItemStack bukkitItemStack);

        Icon.Builder of(org.spongepowered.api.item.inventory.ItemStack spongeItemStack);

        Icon.Builder of(ItemStackContainer itemStack);

        Icon.Builder reset();

        Icon build();
    }
}