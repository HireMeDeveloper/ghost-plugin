package com.hiremedev.thirdproject.Ghosts;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GhostManager {

    private JavaPlugin plugin;
    private List<Ghost> ghosts;

    public GhostManager(JavaPlugin plugin){
        this.plugin = plugin;
        ghosts = new ArrayList<>();
    }

    public void registerGhost(Ghost ghost){
        ghosts.add(ghost);
    }
    public void removeGhost(Ghost ghost){
        ghosts.remove(ghost);
    }

    public List<Ghost> getGhosts(){
        return ghosts;
    }
}
