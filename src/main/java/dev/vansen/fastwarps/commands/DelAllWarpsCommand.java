package dev.vansen.fastwarps.commands;

import dev.vansen.configutils.Configer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DelAllWarpsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command");
            return false;
        }
        Configer.loadAsync("warps/warps.yml").thenAcceptAsync(config -> {
            config.set("warps", null);
            config.saveAsync().thenRunAsync(() -> player.sendMessage("All warps have been deleted!"));
        });
        return true;
    }
}
