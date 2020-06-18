package de.cerus.wlosp.effectboots.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Optional;

import de.cerus.wlosp.effectboots.boots.EffectBoot;
import de.cerus.wlosp.effectboots.boots.EffectBootsController;
import de.cerus.wlosp.effectboots.boots.EffectBootsRegistry;

public class EffectBootsCommand implements CommandExecutor {

    private EffectBootsRegistry bootsRegistry;
    private EffectBootsController bootsController;

    public EffectBootsCommand(EffectBootsRegistry bootsRegistry, EffectBootsController bootsController) {
        this.bootsRegistry = bootsRegistry;
        this.bootsController = bootsController;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;

        if (!player.hasPermission("effectboots.use")) {
            player.sendMessage("§cYou are not allowed to use this command!");
            return true;
        }

        if (args.length == 0) {
            if (bootsController.hasBoot(player)) {
                bootsController.deselectBoot(player);
                player.sendMessage("§aYour boots were removed!");
                return true;
            }

            Inventory inventory = Bukkit.createInventory(null, 9 * 3, "§eEffect Boots");
            EffectBoot[] effectBoots = bootsRegistry.getRegisteredBoots().toArray(new EffectBoot[0]);
            for (int i = 0; i < effectBoots.length; i++) {
                if (i >= inventory.getSize()) {
                    break;
                }

                inventory.setItem(i, effectBoots[i].getDisplayItem());
            }
            player.openInventory(inventory);
            return true;
        }

        String name = String.join(" ", args);
        Optional<EffectBoot> bootOptional = bootsRegistry.getBootByName(name);
        if(!bootOptional.isPresent()) {
            player.sendMessage("§cUnknown boot!");
            return true;
        }

        bootsController.selectBoot(player, bootOptional.get());
        player.sendMessage("§aYour boots were updated!");
        return true;
    }
}
