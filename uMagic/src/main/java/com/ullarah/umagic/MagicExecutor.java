package com.ullarah.umagic;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

class MagicExecutor extends MagicFunctions implements CommandExecutor {

    MagicExecutor() {
        super(false);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) {
            getCommonString().messageNoConsole(sender);
            return true;
        }

        Player player = (Player) sender;
        MagicRecipe recipe = new MagicRecipe();

        switch (command.getName().toLowerCase()) {

            case "hoe":
                giveMagicHoe(player, recipe.hoe());
                break;

        }

        return true;

    }

}