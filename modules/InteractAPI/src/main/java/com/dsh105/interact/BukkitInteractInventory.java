package com.dsh105.interact;

import com.dsh105.commodus.container.ItemStackContainer;
import com.dsh105.commodus.container.PlayerContainer;
import com.dsh105.interact.api.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public class BukkitInteractInventory extends InteractInventory<org.bukkit.inventory.Inventory> implements InventoryHolder, Listener {
    
    protected BukkitInteractInventory(String name, Layout layout, Icon interactIcon, ClickAction clickAction, OpenAction openAction) {
        super(name, layout, interactIcon, clickAction, openAction);
        
        Plugin plugin = (Plugin) Interact.getPlugin();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public org.bukkit.inventory.Inventory getInventory() {
        org.bukkit.inventory.Inventory inventory = Bukkit.createInventory(this, getLayout().getMaximumSize(), getName());
        for (Map.Entry<Integer, Icon> entry : getLayout().getIcons().entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue().asBukkit());
        }
        return inventory;
    }

    @Override
    public void show(PlayerContainer player) {
        super.show(player);
        player.asBukkit().openInventory(getInventory());
    }

    @Override
    public void close(PlayerContainer player) {
        if (player.get() == null) {
            throw new IllegalArgumentException("Provided player is not currently available");
        }
        InventoryHolder holder = player.asBukkit().getOpenInventory().getTopInventory().getHolder();
        if (holder instanceof Inventory && ((Inventory) holder).getId().equals(this.getId())) {
            player.asBukkit().closeInventory();
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            PlayerContainer player = PlayerContainer.of((Player) event.getWhoClicked());
            InventoryHolder holder = event.getWhoClicked().getOpenInventory().getTopInventory().getHolder();
            if (holder instanceof Inventory && event.getRawSlot() >= 0 && event.getRawSlot() < getLayout().getMaximumSize()) {
                event.setCancelled(true);
                if (event.getSlotType().equals(InventoryType.SlotType.OUTSIDE)) {
                    close(player);
                    return;
                }

                handleClick(player, event.getSlot());
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent event) {
        ItemStack stack = event.getItem();
        if (stack != null) {
            event.setCancelled(handleInteract(PlayerContainer.of(event.getPlayer()), ItemStackContainer.of(stack)));
        }
    }

}