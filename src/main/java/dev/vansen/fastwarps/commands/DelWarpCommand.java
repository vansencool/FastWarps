package dev.vansen.fastwarps.commands;

import dev.vansen.configutils.Configer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DelWarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command");
            return false;
        }
        if (args.length != 0) {
            Configer.loadAsync("warps/warps.yml").thenAcceptAsync(config -> {
                if (config.contains("warps." + args[0])) {
                    config.set("warps." + args[0], null);
                    config.saveAsync().thenRunAsync(() -> player.sendMessage("Warp has been deleted!"));
                } else {
                    player.sendMessage("Warp not found!");
                }
            });
        } else {
            player.sendMessage("Please specify a warp name");
        }
        return true;
    }
}
