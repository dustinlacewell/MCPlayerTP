package com.ldlework.mc.PlayerTP;

import de.themoep.inventorygui.*;
import de.themoep.inventorygui.GuiPageElement.PageAction;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.function.Consumer;

import static de.themoep.inventorygui.GuiPageElement.PageAction.NEXT;
import static de.themoep.inventorygui.GuiPageElement.PageAction.PREVIOUS;

public class TPMenu {

    protected final PlayerTP plugin;

    public TPMenu(PlayerTP plugin) {
        this.plugin = plugin;
    }

    protected String[] getLayout() {
        return new String[] {
                "hhhhhhhhh",
                "hhhhhhhhh",
                "hhhhhhhhh",
                "hhhhhhhhh",
                "hhhhhhhhh",
                "p       n"
        };
    }

    protected InventoryGui getGUI(String title) {
        var layout = this.getLayout();
        var gui = new InventoryGui(this.plugin, title, layout);
        gui.setFiller(new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        return gui;
    }

    protected void addPlayer(GuiElementGroup group, Player player, GuiElement.Action action) {
        var head = new ItemStack(Material.PLAYER_HEAD, 1);
        var meta = (SkullMeta) head.getItemMeta();
        meta.setOwningPlayer(player);
        head.setItemMeta(meta);
        var element = new StaticGuiElement('h', head, action, player.getName());
        group.addElement(element);
    }

    protected GuiPageElement getPageButton(char slot, PageAction action, String lore) {
        var stack = new ItemStack(Material.ARROW);
        return new GuiPageElement(slot, stack, action, lore);
    }

    protected void addPageButtons(InventoryGui gui) {
        var prevButton = this.getPageButton('p', PREVIOUS, "Go to previous page (%prevpage%)");
        var nextButton = this.getPageButton('n', NEXT, "Go to previous page (%prevpage%)");
        gui.addElement(prevButton);
        gui.addElement(nextButton);
    }

    public boolean Show(Player player, String title, Consumer<Player> callback) {
        var gui = this.getGUI(title);
        var players = Bukkit.getOnlinePlayers();
        var group = new GuiElementGroup('h');

        for (Player target : players) {
            this.addPlayer(group, target, click -> {
                callback.accept(target);
                gui.close();
                return true;
            });
        }

        gui.addElement(group);
        this.addPageButtons(gui);
        gui.build();
        gui.show(player);
        return true;
    }
}
