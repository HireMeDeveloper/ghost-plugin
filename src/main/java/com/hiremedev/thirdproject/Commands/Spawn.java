package com.hiremedev.thirdproject.Commands;

import com.hiremedev.thirdproject.Ghosts.Ghost;
import com.hiremedev.thirdproject.Ghosts.GhostManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class Spawn implements TabExecutor {
    private JavaPlugin plugin;
    private GhostManager ghostManager;

    private HashMap<String, EntityType> table = new HashMap<String, EntityType>();
    private List<String> keys = new ArrayList<>();

    public Spawn(JavaPlugin plugin, GhostManager ghostManager) {
        this.plugin = plugin;
        this.ghostManager = ghostManager;

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        new Ghost(ghostManager, player.getWorld(), player.getLocation(), EntityType.valueOf(args[0].toUpperCase()));
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            return Arrays.stream(EntityType.values())                       //gets the values of the enum: EntityType
                    .filter(EntityType::isAlive)                            //filters for only EntityTypes that are alive
                    .map(Enum::name)                                        //maps to the name value
                    .filter((name) -> name.contains(args[0].toUpperCase())) //filters for only the names that contain the typed letters
                    .collect(Collectors.toList());                          //collects these up into a list
        }
        return new ArrayList<>();
    }
}
