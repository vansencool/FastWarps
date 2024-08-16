package dev.vansen.fastwarps.tabcompleters;

import com.maximde.pluginsimplifier.PluginHolder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class WarpTabCompleter implements TabCompleter {

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            if (Arrays.stream(Objects.requireNonNull(PluginHolder.getPluginInstance().getDataFolder().listFiles())).noneMatch(file -> file.getName().equals("warps"))) {
                return null;
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
            return list;
        }
        return null;
    }
}
