package com.hiremedev.thirdproject.Listeners;

import com.hiremedev.thirdproject.Items.GhostCrystal;
import com.hiremedev.thirdproject.Players.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONValue;

public class PlayerJoinListener implements Listener {

    JavaPlugin plugin;
    PlayerManager playerManager;

    public PlayerJoinListener(JavaPlugin plugin, PlayerManager playerManager){
        this.plugin = plugin;
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        var player = event.getPlayer();
        if (playerManager.getPlayers().contains(player)) return;

        var inventory = player.getInventory();
        inventory.clear();
        inventory.addItem(new GhostCrystal(plugin));
        playerManager.registerPlayer(player);
    }
}
