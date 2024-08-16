package dev.vansen.fastwarps.tabcompleters;

import dev.vansen.configutils.Configer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WarpTabCompleter implements TabCompleter {

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return new ArrayList<>(Configer.loadAsync("warps/warps.yml")
                .thenApply(config -> config.getConfigurationSection("warps").getKeys(false))
                .join());
    }
}
