package com.hiremedev.thirdproject.Listeners;

import com.hiremedev.thirdproject.Ghosts.GhostManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class GhostDeathListener implements Listener {
    private GhostManager ghostManager;

    public GhostDeathListener(GhostManager ghostManager){
        this.ghostManager = ghostManager;
    }

    @EventHandler
    public void onGhostDie(EntityDeathEvent event){
        Entity target = event.getEntity();
        ghostManager.getGhosts().forEach((ghost)-> {
            if(ghost.getLivingEntity() == (LivingEntity) target){
                ghost.death();
            }
        });
    }
}
