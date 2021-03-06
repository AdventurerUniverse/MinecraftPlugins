package com.ullarah.upostal.command;

import com.ullarah.upostal.PostalInit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;

public class Register {

    public void create(CommandSender sender) {

        try {

            Player player = (Player) sender;
            UUID playerUUID = player.getUniqueId();

            if (!new File(PostalInit.getInboxDataPath(), playerUUID.toString() + ".yml").exists()) {

                boolean dirCreation = true;

                File dataDir = PostalInit.getPlugin().getDataFolder();
                if (!dataDir.exists()) dirCreation = dataDir.mkdir();

                File inboxDir = new File(dataDir + File.separator + "inbox");
                if (!inboxDir.exists()) dirCreation = inboxDir.mkdir();

                if (dirCreation) {

                    boolean fileCreation = true;

                    File inboxConfigFile = new File(inboxDir, playerUUID.toString() + ".yml");
                    if (!inboxConfigFile.exists()) fileCreation = inboxConfigFile.createNewFile();

                    if (fileCreation) {

                        FileConfiguration inboxConfig = YamlConfiguration.loadConfiguration(inboxConfigFile);

                        inboxConfig.set("name", player.getPlayerListName());
                        inboxConfig.set("uuid", playerUUID.toString());
                        inboxConfig.set("slot", 9);
                        inboxConfig.set("blacklist", false);
                        inboxConfig.set("item", new ArrayList<>());

                        inboxConfig.save(inboxConfigFile);

                        PostalInit.getPlugin().getLogger().log(Level.INFO,
                                "Postal Inbox created for " + player.getPlayerListName());

                    }

                }

            } else {

                File inboxConfigFile = new File(PostalInit.getInboxDataPath(), playerUUID.toString() + ".yml");
                FileConfiguration inboxConfig = YamlConfiguration.loadConfiguration(inboxConfigFile);

                if (!inboxConfig.getString("name").equals(player.getPlayerListName())) {

                    inboxConfig.set("name", player.getPlayerListName());
                    inboxConfig.save(inboxConfigFile);

                }

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
