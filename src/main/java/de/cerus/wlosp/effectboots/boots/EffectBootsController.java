package de.cerus.wlosp.effectboots.boots;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EffectBootsController {

    private final Map<UUID, String> selectedBoots = new HashMap<>();

    public boolean selectBoot(Player player, EffectBoot boot) {
        if (hasBoot(player)) {
            return false;
        }

        selectedBoots.put(player.getUniqueId(), boot.getName());
        player.getInventory().setBoots(boot.getDisplayItem());
        return true;
    }

    public boolean deselectBoot(Player player) {
        if (!hasBoot(player)) {
            return false;
        }

        selectedBoots.remove(player.getUniqueId());
        player.getInventory().setBoots(null);
        return true;
    }

    public boolean hasBoot(Player player) {
        return selectedBoots.containsKey(player.getUniqueId());
    }

    public void shutdown() {
        for (UUID uuid : selectedBoots.keySet()) {
            deselectBoot(Bukkit.getPlayer(uuid));
        }
        selectedBoots.clear();
    }

    public Map<UUID, String> getSelectedBoots() {
        return Collections.unmodifiableMap(selectedBoots);
    }

}
