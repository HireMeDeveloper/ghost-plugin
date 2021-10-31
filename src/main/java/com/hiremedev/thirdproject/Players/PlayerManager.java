package com.hiremedev.thirdproject.Players;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class PlayerManager {

    private List<Player> players;
    private HashMap<String,Long> startTimes;

    public PlayerManager(){
        this.startTimes = new HashMap<String,Long>();
        this.players = new ArrayList<>();
    }

    public void registerPlayer(Player player){
        players.add(player);
    }

    public void removePlayer(Player player){
        players.remove(player);
    }

    public List<Player> getPlayers(){
        return players;
    }

    public void setPlayerCooldown(Player player){
        startTimes.put(player.getName(), System.currentTimeMillis());
    }
    public long checkPlayerCooldown(Player player){
        return startTimes.get(player.getName());
    }

    public HashMap<String, Long> getStartTimes() {
        return startTimes;
    }
}
