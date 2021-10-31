package com.hiremedev.thirdproject.Abilities;

public class Ability {
    private long cooldownDuration;

    public Ability(long cooldownDuration) {
        this.cooldownDuration = cooldownDuration;
    }

    public long getCooldownDuration() {
        return cooldownDuration;
    }


}
