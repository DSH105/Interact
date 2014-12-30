package com.dsh105.interact;

import com.dsh105.interact.api.InteractPlugin;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitInteractPlugin extends JavaPlugin implements InteractPlugin {

    @Override
    public void onEnable() {
        Interact.prepare(this);
    }

    @Override
    public void onDisable() {
        Interact.disable();
    }
}