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

import com.dsh105.commodus.sponge.SpongeUtil;
import com.dsh105.interact.api.InteractPlugin;
import com.google.inject.Inject;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.state.PreInitializationEvent;
import org.spongepowered.api.event.state.ServerStoppingEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.service.config.ConfigDir;
import org.spongepowered.api.util.event.Subscribe;

import java.io.File;

@Plugin(id = Version.PLUGIN_ID, name = Version.PLUGIN_NAME, version = Version.PLUGIN_VERSION)
public class SpongeInteractPlugin implements InteractPlugin {

    @Inject
    private Game game;

    @Inject
    private PluginContainer container;

    @Inject
    @ConfigDir(sharedRoot = false)
    private File configDirectory;

    @Subscribe
    public void onPreInitialization(PreInitializationEvent event) {
        Interact.prepare(this, game);
    }

    @Subscribe
    public void onServerStopping(ServerStoppingEvent event) {
        Interact.disable();
    }
}