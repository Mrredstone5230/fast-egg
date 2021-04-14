package me.polishkrowa.fastegg.bukkitrunnables;

import me.polishkrowa.fastegg.FastEgg;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoSaver extends BukkitRunnable {
    @Override
    public void run() {
        FastEgg.plugin.saveConfig();
        FastEgg.plugin.reloadConfig();
    }
}
