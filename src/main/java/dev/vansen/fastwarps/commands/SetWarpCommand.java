package dev.vansen.fastwarps.commands;

import dev.vansen.configutils.Configer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetWarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command");
            return true;
        }
        if (args.length != 0) {
            Configer.loadAsync("warps/" + args[0] + ".yml").thenAcceptAsync(config -> {
                config.set("location", player.getLocation());
                config.saveAsync().thenRunAsync(() -> player.sendMessage("Setted the warp " + args[0] + "!"));
            });
        } else {
            player.sendMessage("Please specify a warp name");
        }
        return true;
    }
}
