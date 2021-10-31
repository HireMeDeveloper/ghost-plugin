package com.hiremedev.thirdproject.Listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemManagementListener implements Listener {

    public ItemManagementListener() {
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        event.setCancelled(true);
        event.getPlayer().sendActionBar(
                Component.text("This seems important, I should keep it.")
                        .color(NamedTextColor.YELLOW)
        );
    }
    public void onPickupItem(EntityPickupItemEvent event){
       if (!(event.getEntity() instanceof Player)) return;
       event.setCancelled(true);
    }

    @EventHandler
    public void onClickItem(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
