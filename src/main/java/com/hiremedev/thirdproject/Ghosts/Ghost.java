package com.hiremedev.thirdproject.Ghosts;

import com.hiremedev.thirdproject.HpBars.HpCloud;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Ghost {

    private LivingEntity livingEntity;
    private GhostManager ghostManager;
    private HpCloud hpCloud;

    public Ghost(GhostManager ghostManager,World world, Location location, EntityType entityType){
        this.ghostManager = ghostManager;
        livingEntity = (LivingEntity) world.spawnEntity(location, entityType);
        livingEntity.addPotionEffect(
                new PotionEffect(
                        PotionEffectType.INVISIBILITY,
                        600000000,
                        1,
                        true,
                        false,
                        false
                )
        );
        hpCloud = new HpCloud(
                world,
                location,
                livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()
        );
        livingEntity.setGlowing(true);
        livingEntity.addPassenger(hpCloud.cloud());
        ghostManager.registerGhost(this);
    }

    public LivingEntity getLivingEntity(){
        return livingEntity;
    }

    public void updateHp(double currentHealth){
        hpCloud.updateHp(currentHealth);
    }
    public void death(){
        ghostManager.removeGhost(this);
        removePassenger();
    }

    public void removePassenger(){
        livingEntity.getPassengers().forEach((passenger)-> passenger.remove());
    }
}
