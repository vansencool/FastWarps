package dev.vansen.fastwarps.commands;

import com.maximde.pluginsimplifier.PluginHolder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class DelAllWarpsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command");
            return true;
        }
        try {
            Files.list(Objects.requireNonNull(new File(PluginHolder.getPluginInstance().getDataFolder(), "warps").toPath()))
                    .parallel()
                    .filter(path -> path.toString().endsWith(".yml"))
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (final IOException e) {
                            e.printStackTrace();
                        }
                    });
            player.sendMessage("All warps have been deleted!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
