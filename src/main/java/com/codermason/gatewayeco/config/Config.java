package com.codermason.gatewayeco.config;

import com.codermason.gatewayeco.GatewayEco;
import com.codermason.gatewayeco.accounts.Account;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.material.Gate;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * created by codermason on 2/17/14
 */
public class Config {

    private static File configFile, playersFile, dataFolder;
    private static FileConfiguration config, players;

    public static void init() {
        dataFolder = GatewayEco.getInstance().getDataFolder();

        configFile = new File(dataFolder, "config.yml");
        playersFile = new File(dataFolder, "players.yml");

        config = YamlConfiguration.loadConfiguration(configFile);
        players = YamlConfiguration.loadConfiguration(playersFile);

        checkFiles(new File[]{configFile, playersFile}, new FileConfiguration[]{config, players});
    }

    private static void checkFiles(File[] files, FileConfiguration[] configs) {
        if(files.length != configs.length) return;

        for(int x=0;x<files.length;x++) {
            File f = files[x];
            FileConfiguration config = configs[x];
            if (!f.exists()) {
                GatewayEco.log("Could not find file " + f.getName() + "! Attempting to create...");
                try {
                    config.save(f);
                }catch (IOException e) {
                    GatewayEco.log("Could not create file " + f.getName() + "!");
                    GatewayEco.shutdown(GatewayEco.ShutdownReason.CANNOT_LOAD_FILES);
                }
                GatewayEco.log("Created file " + f.getName() + " successfully!");
            }
        }
    }

    public static File getConfigFile() {
        return configFile;
    }

    public static File getPlayersFile() {
        return playersFile;
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static FileConfiguration getPlayersConfig() {
        return players;
    }

    public static void saveFile(File f, FileConfiguration config) {
        try {
            config.save(f);
        }catch(Exception e) {
            GatewayEco.log("Could not save file "+f.getName()+"!", Level.WARNING);
        }
    }

    public static int getBalance(String player) {
        return getPlayersConfig().getInt(player);
    }

    public static void updateAccount(Account account) {
        getPlayersConfig().set(account.getPlayer(), account.getBalance());
        saveFile(getPlayersFile(), getPlayersConfig());
    }

}
