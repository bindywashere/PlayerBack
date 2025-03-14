package org.bindywashere.backplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TeleportListener implements Listener {

    private final org.bindywashere.backplugin.BackPlugin plugin;

    public TeleportListener(org.bindywashere.backplugin.BackPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        plugin.setLastLocation(event.getPlayer());
    }
}
