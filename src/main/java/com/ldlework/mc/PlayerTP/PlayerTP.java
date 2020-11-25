package com.ldlework.mc.PlayerTP;

import co.aikar.commands.PaperCommandManager;
import com.ldlework.mc.PlayerTP.commands.HereCommand;
import com.ldlework.mc.PlayerTP.commands.ThereCommand;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * List all players in chat as clickable teleport links.
 *
 * @author ldlework
 * @version 0.1-SNAPSHOT
 * @since 0.1-SNAPSHOT
 */
public class PlayerTP extends JavaPlugin {
    private PaperCommandManager commands;

    private PaperCommandManager setupCommandManager() {
        var commands = new PaperCommandManager(this);
        commands.registerCommand(new HereCommand(this));
        commands.registerCommand(new ThereCommand(this));
        return commands;
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.reloadConfig();
        this.commands = this.setupCommandManager();
    }
}
