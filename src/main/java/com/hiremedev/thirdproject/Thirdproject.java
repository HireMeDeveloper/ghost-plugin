package com.hiremedev.thirdproject;

import com.hiremedev.thirdproject.Abilities.AbilityManager;
import com.hiremedev.thirdproject.Commands.Spawn;
import com.hiremedev.thirdproject.Ghosts.GhostManager;
import com.hiremedev.thirdproject.Listeners.*;
import com.hiremedev.thirdproject.Players.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Thirdproject extends JavaPlugin {
    @Override
    public void onEnable() {
        //Create mangers
        GhostManager ghostManager = new GhostManager(this);
        PlayerManager playerManager = new PlayerManager();
        AbilityManager abilityManager = new AbilityManager(this, playerManager);

        //Listeners
        getServer().getPluginManager().registerEvents(new GhostDamageListener(ghostManager), this);
        getServer().getPluginManager().registerEvents(new GhostDeathListener(ghostManager), this);
        getServer().getPluginManager().registerEvents(new PlayerHitGhostListener(ghostManager), this);

        getServer().getPluginManager().registerEvents(new ItemUseListener(this, playerManager, abilityManager), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this, playerManager), this);

        getServer().getPluginManager().registerEvents(new ItemManagementListener(), this);

        //Custom commands
        Spawn spawn = new Spawn(this, ghostManager);
        getCommand("spawn").setExecutor(spawn);
        getCommand("spawn").setTabCompleter(spawn);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
