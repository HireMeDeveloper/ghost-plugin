package com.hiremedev.thirdproject.Abilities;

import com.hiremedev.thirdproject.Players.PlayerManager;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AbilityManager {
    private JavaPlugin plugin;
    private PlayerManager playerManager;

    public AbilityManager(JavaPlugin plugin, PlayerManager playerManager) {
        this.plugin = plugin;
        this.playerManager = playerManager;
    }

    public void useCrystalAbility(Player player) {
        var world = player.getWorld();
        var location = player.getLocation();

        var cloud = (AreaEffectCloud) world.spawnEntity(location, EntityType.AREA_EFFECT_CLOUD);
        cloud.addCustomEffect(
                new PotionEffect(PotionEffectType.SLOW, 30, 2),
                true
        );
        cloud.setDuration(120);
        cloud.setRadius(5);
        var task = new EntityFollowPlayerTask(cloud, player).runTaskTimer(plugin, 0, 10);
    }
}
