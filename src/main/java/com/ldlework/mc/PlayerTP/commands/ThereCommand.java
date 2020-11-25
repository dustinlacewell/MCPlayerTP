package com.ldlework.mc.PlayerTP.commands;

import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.ldlework.mc.PlayerTP.PlayerTP;
import org.bukkit.entity.Player;

@CommandAlias("there")
public class ThereCommand extends PTPCommand {

    public ThereCommand(PlayerTP plugin) {
        super(plugin);
    }

    @Override
    String getActionCommand() {
        return "there_command";
    }

    @Override
    String getGUITitle() {
        return "Teleport to a player";
    }

    @Default
    @Description("Teleport to a player")
    @CommandCompletion("@players")
    public void onCmd(Player player, @Optional OnlinePlayer target) {
        super.onCmd(player, target);
    }
}
