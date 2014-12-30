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
import org.bukkit.plugin.Plugin;

import java.util.Map;

public class BukkitInteractInventory extends InteractInventory implements InventoryHolder, Listener {

    protected BukkitInteractInventory(String name, Layout layout, Icon interactIcon, ClickAction clickAction, OpenAction openAction) {
        super(name, layout, interactIcon, clickAction, openAction);
        Plugin plugin = (Plugin) Interact.getPlugin();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public org.bukkit.inventory.Inventory getInventory() {
        return Bukkit.createInventory(this, getLayout().getMaximumSize(), getName());
    }

    @Override
    public void show(PlayerContainer player) {
        super.show(player);
        org.bukkit.inventory.Inventory inventory = getInventory();
        for (Map.Entry<Integer, Icon> entry : getLayout().getIcons().entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue().asBukkit());
        }
        player.asBukkit().openInventory(inventory);
    }

    @Override
    public void close(PlayerContainer player) {
        super.close(player);
        InventoryHolder holder = player.asBukkit().getOpenInventory().getTopInventory().getHolder();
        if (holder instanceof Inventory) {
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
        boolean result = handleInteract(PlayerContainer.of(event.getPlayer()), ItemStackContainer.of(event.getItem()));
        event.setCancelled(result);
    }

}