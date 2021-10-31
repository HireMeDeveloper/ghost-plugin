package com.hiremedev.thirdproject.Abilities;

import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class EntityFollowPlayerTask extends BukkitRunnable {

    private Player player;
    private Entity cloud;

    public EntityFollowPlayerTask(Entity cloud,Player player) {
        this.player = player;
        this.cloud = cloud;
    }

    @Override
    public void run() {
        if(cloud.isDead()) this.cancel();
        cloud.teleport(player.getLocation());

        var bounds = cloud.getBoundingBox();
        cloud.getNearbyEntities(bounds.getWidthX(), bounds.getHeight(), bounds.getWidthZ()).forEach((target)->{
            if(!(target instanceof LivingEntity)) return;
            if(target instanceof AreaEffectCloud || target instanceof Player) return;
            var LivingEntity = (LivingEntity) target;
            target.setLastDamageCause(new EntityDamageEvent(player, EntityDamageEvent.DamageCause.MAGIC, .1));
        });
    }
}
