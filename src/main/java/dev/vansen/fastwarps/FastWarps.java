package dev.vansen.fastwarps;

import com.maximde.pluginsimplifier.PluginHolder;
import com.maximde.pluginsimplifier.command.CommandRegistrar;
import dev.vansen.configutils.ConfigUtils;
import dev.vansen.fastwarps.commands.SetWarpCommand;
import dev.vansen.fastwarps.commands.WarpCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class FastWarps extends JavaPlugin {

    @Override
    public void onEnable() {
        ConfigUtils.init(this);
        PluginHolder.setPluginInstance(this);
        CommandRegistrar.command("warp", new WarpCommand())
                .namespace("warps")
                .aliases(List.of("w"))
                .permission("fastwarps.warp")
                .register();

        CommandRegistrar.command("setwarp", new SetWarpCommand())
                .namespace("warps")
                .aliases(List.of("sw", "swarp", "setw", "setwar"))
                .permission("fastwarps.setwarp")
                .register();
    }
}
