package dev.vansen.fastwarps;

import com.maximde.pluginsimplifier.PluginHolder;
import dev.vansen.configutils.ConfigUtils;
import dev.vansen.fastwarps.commands.Registrar;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@SuppressWarnings("unused")
public class FastWarps extends JavaPlugin {

    @Override
    public void onEnable() {
        ConfigUtils.init(this);
        PluginHolder.setPluginInstance(this);
        Registrar.get().register();
        save();
    }

    protected void save() {
        File counting = new File(getDataFolder(), "counting.yml");
        if (!counting.exists()) {
            saveResource("counting.yml", false);
        }
    }
}
