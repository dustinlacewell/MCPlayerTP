package com.ldlework.mc.PlayerTP.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.ldlework.mc.PlayerTP.PlayerTP;
import com.ldlework.mc.PlayerTP.TPMenu;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public abstract class PTPCommand extends BaseCommand {

    protected PlayerTP plugin;

    public PTPCommand(PlayerTP plugin) {
        this.plugin = plugin;
    }

    abstract String getActionCommand();
    abstract String getGUITitle();

    public void doCmd(Player self, Player target, FileConfiguration config) {
        var selfName = self.getName();
        var targetName = target.getName();
        var template = config.getString(this.getActionCommand());
        var command = template
                .replaceAll("%self%", selfName)
                .replaceAll("%target%", targetName);
        self.chat(command);
    }

    protected void onCmd(Player player, OnlinePlayer target) {
        var config = this.plugin.getConfig();

        if (target != null) {
            this.doCmd(player, target.getPlayer(), config);
        } else {
            var title = this.getGUITitle();
            var menu = new TPMenu(plugin);
            menu.Show(player, title, selectedTarget -> this.doCmd(player, selectedTarget, config));
        }
    }

}
