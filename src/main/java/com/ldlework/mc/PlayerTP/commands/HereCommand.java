package com.ldlework.mc.PlayerTP.commands;

import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.ldlework.mc.PlayerTP.PlayerTP;
import org.bukkit.entity.Player;

@CommandAlias("here")
public class HereCommand extends PTPCommand {

    public HereCommand(PlayerTP plugin) {
        super(plugin);
    }

    @Override
    String getActionCommand() {
        return "here_command";
    }

    @Override
    String getGUITitle() {
        return "Teleport a player to you";
    }

    @Default
    @Description("Teleport a player to you")
    @CommandCompletion("@players")
    public void onCmd(Player player, @Optional OnlinePlayer target) {
        super.onCmd(player, target);
    }
}
