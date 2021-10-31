package com.hiremedev.thirdproject.Listeners;

import com.hiremedev.thirdproject.Ghosts.GhostManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class PlayerHitGhostListener implements Listener {
    private GhostManager ghostManager;

    public PlayerHitGhostListener(GhostManager ghostManager){
        this.ghostManager = ghostManager;
    }

    @EventHandler
    public void onGhostDie(EntityDamageByEntityEvent event){
        Entity target = event.getEntity();
        ghostManager.getGhosts().forEach((ghost)-> {
            if(ghost.getLivingEntity() == (LivingEntity) target){
                event.setCancelled(true);
            }
        });
    }
}
