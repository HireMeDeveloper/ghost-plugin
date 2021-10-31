package com.hiremedev.thirdproject.Items;

import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class GhostCrystal extends CustomItem implements UseableItem{

    public GhostCrystal(JavaPlugin plugin) {
        super(
                Material.EMERALD,
                1,
                "Ghost Crystal",
                NamedTextColor.GREEN,
                new NamespacedKey(plugin, "ghost_crystal_key"),
                "ghost_crystal"
        );
    }

    public void useItem() {
        Bukkit.getLogger().info("You used your custom item");
    }
}
