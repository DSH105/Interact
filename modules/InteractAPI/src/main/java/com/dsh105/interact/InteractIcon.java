package com.dsh105.interact;

import com.dsh105.commodus.bukkit.ItemRegistry;
import com.dsh105.commodus.container.ItemStackContainer;
import com.dsh105.commodus.sponge.SpongeUtil;
import com.dsh105.interact.api.Icon;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.api.item.ItemType;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("typeId", typeId);
        map.put("name", name);
        map.put("lore", lore);
        map.put("quantity", quantity);
        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InteractIcon)) return false;

        InteractIcon that = (InteractIcon) o;

        if (quantity != that.quantity) return false;
        if (!lore.equals(that.lore)) return false;
        if (!name.equals(that.name)) return false;
        if (!typeId.equals(that.typeId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = typeId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + lore.hashCode();
        result = 31 * result + quantity;
        return result;
    }
}