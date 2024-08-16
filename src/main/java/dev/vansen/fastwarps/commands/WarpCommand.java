package dev.vansen.fastwarps.commands;

import dev.vansen.configutils.Configer;
import io.papermc.lib.PaperLib;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command");
            return false;
        }
        if (args.length != 0) {
            Configer.loadAsync("warps/warps.yml").thenAcceptAsync(config -> {
                if (config.contains("warps." + args[0])) {
                    PaperLib.teleportAsync(player, config.getLocation("warps." + args[0]));
                    player.sendMessage("Teleported to warp " + args[0] + "!");
                } else {
                    player.sendMessage("Warp not found!");
                }
            });
        } else {
            player.sendMessage("Usage: /warp <warp>");
        }
        return false;
    }
}
