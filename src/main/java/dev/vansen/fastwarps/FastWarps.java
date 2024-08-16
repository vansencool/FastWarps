package dev.vansen.fastwarps;

import com.maximde.pluginsimplifier.PluginHolder;
import com.maximde.pluginsimplifier.command.CommandRegistrar;
import dev.vansen.configutils.ConfigUtils;
import dev.vansen.fastwarps.commands.DelAllWarpsCommand;
import dev.vansen.fastwarps.commands.DelWarpCommand;
import dev.vansen.fastwarps.commands.SetWarpCommand;
import dev.vansen.fastwarps.commands.WarpCommand;
import dev.vansen.fastwarps.tabcompleters.WarpTabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class FastWarps extends JavaPlugin {

    @Override
    public void onEnable() {
        ConfigUtils.init(this);
        PluginHolder.setPluginInstance(this);
        CommandRegistrar.command("warp", new WarpCommand())
                .aliases(List.of("w"))
                .permission("fastwarps.warp")
                .completer(new WarpTabCompleter())
                .register();

        CommandRegistrar.command("setwarp", new SetWarpCommand())
                .aliases(Arrays.asList("sw", "swarp", "setw", "setwar"))
                .permission("fastwarps.setwarp")
                .register();

        CommandRegistrar.command("delwarp", new DelWarpCommand())
                .aliases(Arrays.asList("dw", "dewarp", "delw", "delwar"))
                .permission("fastwarps.delwarp")
                .completer(new WarpTabCompleter())
                .register();

        CommandRegistrar.command("delallwarps", new DelAllWarpsCommand())
                .aliases(Arrays.asList("daw", "delallwarp"))
                .permission("fastwarps.delallwarp")
                .register();
    }
}
