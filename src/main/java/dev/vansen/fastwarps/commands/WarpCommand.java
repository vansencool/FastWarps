package dev.vansen.fastwarps.commands;

import com.maximde.pluginsimplifier.PluginHolder;
import dev.vansen.configutils.Configer;
import io.papermc.lib.PaperLib;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class WarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command");
            return true;
        }
        if (args.length != 0) {
            File file = new File(PluginHolder.getPluginInstance().getDataFolder(), "warps/" + args[0] + ".yml");
            if (!file.exists()) {
                player.sendMessage("Warp not found!");
                return true;
            }
            Configer.loadAsync("warps/" + args[0] + ".yml").thenAcceptAsync(config -> PaperLib.teleportAsync(player, config.getLocation("location")).thenRunAsync(() -> player.sendMessage("Teleported to warp " + args[0] + "!")));
        } else {
            player.sendMessage("Usage: /warp <warp>");
        }
        return false;
    }
}
