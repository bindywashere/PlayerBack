package org.bindywashere.backplugin;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

// нет пути, это литтерали мой первый плагин после того, как я изучил базовую джаву и подучил bukkitapi

public class BackPlugin extends JavaPlugin implements CommandExecutor {

    private final HashMap<UUID, Location> lastLocations = new HashMap<>();

    @Override
    public void onEnable() {
        getCommand("back").setExecutor(this);
        getServer().getPluginManager().registerEvents(new TeleportListener(this), this);
        getLogger().info("PlayerBack успешно загружен!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§bЭту команду могут использовать только игроки!");
            return true;
        }

        Player player = (Player) sender;
        UUID playerUUID = player.getUniqueId();

        if (!lastLocations.containsKey(playerUUID)) {
            player.sendMessage("§4У игрока нет предыдущего местоположения!");
            return true;
        }

        Location lastLocation = lastLocations.get(playerUUID);
        player.teleport(lastLocation);
        player.sendMessage("§bИгрок был возвращён на предыдущее местоположение");

        return true;
    }
// после предыдущей строки, кстати, я захотел жрать
    public void setLastLocation(Player player) {
        lastLocations.put(player.getUniqueId(), player.getLocation());
    }
}
