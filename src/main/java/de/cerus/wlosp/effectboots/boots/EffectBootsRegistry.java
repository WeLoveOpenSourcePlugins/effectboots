package de.cerus.wlosp.effectboots.boots;

import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class EffectBootsRegistry {

    private final Set<EffectBoot> registeredBoots = new HashSet<>();

    public void registerBoots(EffectBoot... boots) {
        registeredBoots.addAll(Arrays.asList(boots));
    }

    public Optional<EffectBoot> getBootByDisplayItem(ItemStack item) {
        return registeredBoots.stream()
                .filter(boot -> boot.getDisplayItem().equals(item))
                .findAny();
    }

    public Optional<EffectBoot> getBootByName(String name) {
        return registeredBoots.stream()
                .filter(boot -> boot.getName().equalsIgnoreCase(name))
                .findAny();
    }

    public void shutdown() {
        registeredBoots.clear();
    }

    public Set<EffectBoot> getRegisteredBoots() {
        return Collections.unmodifiableSet(registeredBoots);
    }

}
