package dev.vansen.fastwarps;

import com.maximde.pluginsimplifier.PluginHolder;
import dev.vansen.configutils.ConfigUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class FastWarps extends JavaPlugin {

    @Override
    public void onEnable() {
        // TODO
        ConfigUtils.init(this);
        PluginHolder.setPluginInstance(this);
    }
}
