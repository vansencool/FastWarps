package dev.vansen.fastwarps.commands;

import com.maximde.pluginsimplifier.command.CommandRegistrar;
import dev.vansen.fastwarps.tabcompleters.WarpTabCompleter;
import dev.vansen.fastwarps.tabcompleters.WarpsTabCompleter;

import java.util.Arrays;
import java.util.List;

public class Registrar {
    private static final Registrar instance = new Registrar();

    private Registrar() {

    }

    public static Registrar get() {
        return instance;
    }

    public void register() {
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

        CommandRegistrar.command("warps", new WarpsCommand())
                .permission("fastwarps.warps")
                .completer(new WarpsTabCompleter())
                .register();
    }
}
