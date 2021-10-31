package com.hiremedev.thirdproject.Listeners;

import com.hiremedev.thirdproject.Abilities.AbilityManager;
import com.hiremedev.thirdproject.Items.CustomItem;
import com.hiremedev.thirdproject.Items.GhostCrystal;
import com.hiremedev.thirdproject.Items.UseableItem;
import com.hiremedev.thirdproject.Players.PlayerManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import javax.naming.Name;
import java.util.AbstractList;
import java.util.List;

public class ItemUseListener implements Listener {

    private JavaPlugin plugin;
    private PlayerManager playerManager;
    private AbilityManager abilityManager;

    public ItemUseListener(JavaPlugin plugin, PlayerManager playerManager, AbilityManager abilityManager){
        this.plugin = plugin;
        this.playerManager = playerManager;
        this.abilityManager = abilityManager;
    }

    @EventHandler
    public void onItemUse(PlayerInteractEvent event){
        if (event.getHand() == EquipmentSlot.OFF_HAND) return;
        if(event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        var item = event.getPlayer().getInventory().getItemInMainHand();
        if(item.getItemMeta() == null) return;
        var itemData = item.getItemMeta().getPersistentDataContainer();
        if (itemData.has(new NamespacedKey(plugin, "ghost_crystal_key"), PersistentDataType.STRING)) {
            useCrystalItem(event.getPlayer(), 5 * 1000);
        }
    }

    private void useCrystalItem(Player player, long total){
        var startTimes = playerManager.getStartTimes();
        var playerName = player.getName();
        if(startTimes.get(playerName) == null) {
            startTimes.put(playerName, System.currentTimeMillis());
            abilityManager.useCrystalAbility(player);
            return;
        }

        var start = startTimes.get(playerName);
        var end = startTimes.get(playerName) + total;
        var current = System.currentTimeMillis();
        var delta = current - start;
        var remaining = end - current;

        if(System.currentTimeMillis() < end) {
            player.sendActionBar(Component
                    .text()
                    .append(Component.text("On Cooldown for "))
                    .append(Component
                            .text((float) remaining / 1000)
                            .color(NamedTextColor.BLUE)
                    )
                    .append(Component.text(" seconds."))
                    .color(NamedTextColor.WHITE)
                    .build()
            );
            return;
        }

        startTimes.put(playerName, current);
        abilityManager.useCrystalAbility(player);
    }
}
