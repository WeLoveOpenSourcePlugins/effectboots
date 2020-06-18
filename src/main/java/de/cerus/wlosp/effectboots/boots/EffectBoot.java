package de.cerus.wlosp.effectboots.boots;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class EffectBoot {

    private String name;
    private int interval;

    public EffectBoot(String name) {
        this(name, 5);
    }

    public EffectBoot(String name, int interval) {
        this.name = name;
        this.interval = interval;
    }

    public abstract void playEffect(Player player, Location location);

    public abstract ItemStack getDisplayItem();

    public String getName() {
        return name;
    }

    public int getInterval() {
        return interval;
    }

}
