package com.ldlework.mc.PlayerTP;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * List all players in chat as clickable teleport links.
 *
 * @author ldlework
 * @version 0.1-SNAPSHOT
 * @since 0.1-SNAPSHOT
 */
public class PluginEntry extends JavaPlugin {
    private String here_command;
    private String there_command;

    private void setCommands() {
        this.here_command = this.getConfig().getString("here_command");
        this.there_command = this.getConfig().getString("there_command");
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.setCommands();
    }

    private void loadConfig() {
        this.reloadConfig();
        this.setCommands();
    }

    private TextComponent makeLink(String label, String command, String self, String target) {
        var event = new ClickEvent(ClickEvent.Action.RUN_COMMAND, command
                .replaceAll("%self%", self)
                .replaceAll("%target%", target)
        );
        var text = new TextComponent(label);
        text.setColor(ChatColor.YELLOW);
        text.setClickEvent(event);
        return text;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("ptp")) {
            return false;
        }
        this.loadConfig();
        sender.sendMessage("Online players:");
        Bukkit.getOnlinePlayers()
                .stream()
                .forEach(pl -> {
                    var action = ClickEvent.Action.RUN_COMMAND;
                    var selfName = sender.getName();
                    var targetName = pl.getName();
                    var text = new TextComponent(String.format("%s: ", targetName));
                    var here = this.makeLink("[here]", this.here_command, selfName, targetName);
                    var there = this.makeLink("[there]", this.there_command, selfName, targetName);

                    text.addExtra(here);
                    text.addExtra(" ");
                    text.addExtra(there);
                    sender.sendMessage(text);
                });
        return true;
    }
}
