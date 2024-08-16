package dev.vansen.fastwarps.commands;

import dev.vansen.configutils.Configer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class WarpsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command");
            return true;
        }

        Inventory inventory = Bukkit.createInventory(null, 9 * 3, "Warps");
        ArrayList<String> warps = new ArrayList<>(Configer.loadAsync("warps/warps.yml")
                .thenApply(config -> config.getConfigurationSection("warps").getKeys(false))
                .join());

        for (String warp : warps) {
            ItemStack item = new ItemStack(Material.PAPER);
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(warp);
            }
            item.setItemMeta(meta);
            inventory.addItem(item);
        }

        player.openInventory(inventory);
        return true;
    }
}
