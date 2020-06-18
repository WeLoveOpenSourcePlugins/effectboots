package de.cerus.wlosp.effectboots.task;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

import de.cerus.wlosp.effectboots.boots.EffectBoot;
import de.cerus.wlosp.effectboots.boots.EffectBootsRegistry;
import de.cerus.wlosp.effectboots.boots.EffectBootsController;

public class ParticleTask extends BukkitRunnable {

    private final Map<String, Integer> bootIntervalMap = new HashMap<>();

    private EffectBootsRegistry bootRegistry;
    private EffectBootsController bootsController;

    public ParticleTask(EffectBootsRegistry bootRegistry, EffectBootsController bootsController) {
        this.bootRegistry = bootRegistry;
        this.bootsController = bootsController;
    }

    @Override
    public void run() {
        for (EffectBoot boot : bootRegistry.getRegisteredBoots()) {
            int remaining = bootIntervalMap.getOrDefault(boot.getName(), 0);
            if (remaining > 0) {
                bootIntervalMap.put(boot.getName(), --remaining);
                continue;
            }

            bootsController.getSelectedBoots().entrySet().stream()
                    .filter(entry -> entry.getValue().equals(boot.getName()))
                    .map(entry -> Bukkit.getPlayer(entry.getKey()))
                    .filter(player -> player != null && player.isOnline())
                    .forEach(player -> boot.playEffect(player, player.getLocation()));
            bootIntervalMap.put(boot.getName(), boot.getInterval());
        }
    }
}
