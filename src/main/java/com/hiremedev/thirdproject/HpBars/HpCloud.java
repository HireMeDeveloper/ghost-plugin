package com.hiremedev.thirdproject.HpBars;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class HpCloud {
    private AreaEffectCloud cloud;
    private double totalHealth;

    public HpCloud(World world, Location location, double totalHealth) {
        this.totalHealth = totalHealth;
        cloud = (AreaEffectCloud) world.spawnEntity(location, EntityType.AREA_EFFECT_CLOUD);
        cloud.customName(createHpComponent(totalHealth));
        cloud.setParticle(Particle.BLOCK_CRACK, Material.AIR.createBlockData());
        cloud.setCustomNameVisible(true);
        cloud.setDuration(600000000);
    }

    private Component createHpComponent(double currentHealth) {
        Component component = Component.text("[").color(NamedTextColor.WHITE);
        for (int i = 1; i < totalHealth; i++) {
            component = component.append(
                    (i < currentHealth) ?
                            Component.text("|").color(NamedTextColor.GREEN) :
                            Component.text("|").color(NamedTextColor.RED)
            );
        }
        return component.append(Component.text("]").color(NamedTextColor.WHITE));
    }
    public void updateHp(double currentHealth){
        cloud.customName(createHpComponent(currentHealth));
    }
    public Entity cloud() {
        return cloud;
    }
    public void removeCloud() {
        cloud.remove();
    }
}
