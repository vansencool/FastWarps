package dev.vansen.fastwarps.commands;

import com.maximde.pluginsimplifier.PluginHolder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DelWarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command");
            return false;
        }
        if (args.length != 0) {
            Path warp = PluginHolder.getPluginInstance().getDataFolder().toPath().resolve("warps/" + args[0] + ".yml");

            if (Files.exists(warp)) {
                try {
                    Files.delete(warp);
                    player.sendMessage("Warp has been deleted!");
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            } else {
                player.sendMessage("Warp not found!");
            }
        } else {
            player.sendMessage("Please specify a warp name");
        }
        return true;
    }
}