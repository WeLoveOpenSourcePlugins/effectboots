package de.cerus.wlosp.effectboots.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

import de.cerus.wlosp.effectboots.boots.EffectBoot;
import de.cerus.wlosp.effectboots.boots.EffectBootsController;
import de.cerus.wlosp.effectboots.boots.EffectBootsRegistry;

public class InventoryClickListener implements Listener {

    private EffectBootsRegistry bootsRegistry;
    private EffectBootsController bootsController;

    public InventoryClickListener(EffectBootsRegistry bootsRegistry, EffectBootsController bootsController) {
        this.bootsRegistry = bootsRegistry;
        this.bootsController = bootsController;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("§eEffect Boots")) {
            return;
        }
        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        if (!player.hasPermission("effectboots.use")) {
            return;
        }

        ItemStack currentItem = event.getCurrentItem();
        if (currentItem == null) {
            return;
        }

        Optional<EffectBoot> bootOptional = bootsRegistry.getBootByDisplayItem(currentItem);
        if (!bootOptional.isPresent()) {
            return;
        }

        if (bootsController.hasBoot(player)) {
            bootsController.deselectBoot(player);
        }
        bootsController.selectBoot(player, bootOptional.get());
    }

}
