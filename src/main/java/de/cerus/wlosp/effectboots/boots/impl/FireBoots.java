package de.cerus.wlosp.effectboots.boots.impl;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.cerus.wlosp.effectboots.boots.EffectBoot;
import de.cerus.wlosp.effectboots.util.ItemBuilder;

public class FireBoots extends EffectBoot {

    public FireBoots() {
        super("fire-boots");
    }

    @Override
    public void playEffect(Player player, Location location) {
        location.getWorld().spawnParticle(Particle.FLAME, location, 10, 0.25, 0.25, 0.25);
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.LEATHER_BOOTS)
                .setDisplayName("§6Fire Boots")
                .setLeatherColor(Color.ORANGE)
                .build();
    }

}
