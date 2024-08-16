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
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class WarpsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command");
            return true;
        }

        if (args.length == 0 || Integer.parseInt(args[0]) < 1) {
            player.sendMessage("Usage: /warps <page>");
            return true;
        }

        if (!new File(PluginHolder.getPluginInstance().getDataFolder(), "warps").exists() && Arrays.stream(Objects.requireNonNull(new File(PluginHolder.getPluginInstance().getDataFolder(), "warps").listFiles()))
                .anyMatch(file -> file.getName().endsWith(".yml"))) {
            player.sendMessage("No warps were found!");
            return true;
        }
        List<String> list = null;
        try {
            list = Files.list(Objects.requireNonNull(new File(PluginHolder.getPluginInstance().getDataFolder(), "warps").toPath()))
                    .parallel()
                    .filter(path -> path.toString().endsWith(".yml"))
                    .map(Path::getFileName)
                    .map(fileName -> fileName.toString().substring(0, fileName.toString().lastIndexOf('.')))
                    .toList();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        if (Integer.parseInt(args[0]) <= 0 || Integer.parseInt(args[0]) > (int) Math.ceil((double) list.size() / 10)) {
            player.sendMessage("Invalid page number. Please enter a number between 1 and " + (int) Math.ceil((double) list.size() / 10) + ".");
            return true;
        }

        player.sendMessage("List of warps - Page " + args[0] + ":");
        List<String> finalList = list;
        IntStream.rangeClosed((Integer.parseInt(args[0]) - 1) * 10, Math.min((Integer.parseInt(args[0]) - 1) * 10 + 10, list.size()) - 1)
                .mapToObj(i -> (i + 1) + ": " + finalList.get(i))
                .forEach(player::sendMessage);

        return true;

    }
}