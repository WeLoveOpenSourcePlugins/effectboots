package de.cerus.wlosp.effectboots;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.cerus.wlosp.effectboots.boots.EffectBoot;
import de.cerus.wlosp.effectboots.boots.EffectBootsController;
import de.cerus.wlosp.effectboots.boots.EffectBootsRegistry;
import de.cerus.wlosp.effectboots.boots.impl.EmeraldBoots;
import de.cerus.wlosp.effectboots.boots.impl.FireBoots;
import de.cerus.wlosp.effectboots.boots.impl.HeartBoots;
import de.cerus.wlosp.effectboots.command.EffectBootsCommand;
import de.cerus.wlosp.effectboots.listener.InventoryClickListener;
import de.cerus.wlosp.effectboots.task.ParticleTask;

public class EffectBootsPlugin extends JavaPlugin {

    private Set<EffectBoot> unregisteredBoots = new HashSet<>();
    private EffectBootsRegistry bootRegistry;
    private EffectBootsController bootsController;
    private ParticleTask particleTask;

    @Override
    public void onEnable() {
        bootRegistry = new EffectBootsRegistry();
        bootRegistry.registerBoots(
                new HeartBoots(),
                new EmeraldBoots(),
                new FireBoots()
        );

        bootRegistry.registerBoots(unregisteredBoots.toArray(new EffectBoot[0]));
        unregisteredBoots = null;

        bootsController = new EffectBootsController();

        particleTask = new ParticleTask(bootRegistry, bootsController);
        particleTask.runTaskTimer(this, 1, 1);

        getCommand("effectboots").setExecutor(new EffectBootsCommand(bootRegistry, bootsController));
        getServer().getPluginManager().registerEvents(new InventoryClickListener(bootRegistry, bootsController), this);
    }

    @Override
    public void onDisable() {
        particleTask.cancel();
        bootRegistry.shutdown();
        bootsController.shutdown();
    }

    public boolean registerEffectBoots(EffectBoot... boots) {
        return registerEffectBoots(Arrays.asList(boots));
    }

    public boolean registerEffectBoots(Collection<EffectBoot> collection) {
        if (unregisteredBoots == null) {
            return false;
        }

        return unregisteredBoots.addAll(collection);
    }

}
