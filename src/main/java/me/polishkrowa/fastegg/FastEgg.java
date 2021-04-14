package me.polishkrowa.fastegg;

import me.polishkrowa.fastegg.bukkitrunnables.AutoSaver;
import me.polishkrowa.fastegg.bukkitrunnables.EggRunnable;
import me.polishkrowa.fastegg.events.PaperEvents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class FastEgg extends JavaPlugin {

    public static boolean isRunningPaper = false;
    public static FastEgg plugin;
    public static HashMap<Integer, EggObject> eggs = new HashMap<>();

    @Override
    public void onEnable() {
        plugin = this;

        try {
            Class.forName("co.aikar.timings.Timing");
            isRunningPaper = true;
        } catch (ClassNotFoundException e) {
            isRunningPaper = false;
            Bukkit.getConsoleSender().sendMessage("[Fast-Egg] Note that some features require PaperMC for this plugin to work properly");
        }

        if (isRunningPaper)
            this.getServer().getPluginManager().registerEvents(new PaperEvents(), this);

        new AutoSaver().runTaskTimer(this, 0, 3600);

        this.saveDefaultConfig();
        boolean toDisable = false;
        if (this.getConfig().get("laying-time") == null) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[Fast-Egg] laying-time is invalid in config file. Disabling Plugin");
            toDisable = true;
        }

        if (toDisable) {
            Bukkit.getServer().getPluginManager().disablePlugin(this);
        }
        eggs.clear();

        for (String key : this.getConfig().getConfigurationSection("eggs").getKeys(false)) {
            if (this.getConfig().getLocation("eggs." + key + ".loc").getBlock().equals(Material.TURTLE_EGG))
                eggs.put(Integer.parseInt(key), new EggObject(this.getConfig().getLocation("eggs." + key + ".loc"),this.getConfig().getConfigurationSection("eggs").getInt(key + ".ticks")));
            else
                this.getConfig().set("eggs." + key, null);
        }
        //eggs:
        //  1903743(random):
        //    loc: afdaf
        //    ticks: 1


        new EggRunnable().runTaskTimer(this, 0, 20);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.saveConfig();
    }
}
