package dev.vansen.fastwarps;

import dev.vansen.configutils.ConfigUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class FastWarps extends JavaPlugin {

    @Override
    public void onEnable() {
        // TODO
        ConfigUtils.init(this);
    }
}
