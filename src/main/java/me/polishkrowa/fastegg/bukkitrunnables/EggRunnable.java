package me.polishkrowa.fastegg.bukkitrunnables;

import me.polishkrowa.fastegg.FastEgg;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

public class EggRunnable extends BukkitRunnable {

    @Override
    public void run() {
        FastEgg.eggs.forEach((random, object) -> {
            if (!object.location.getBlock().getType().equals(Material.TURTLE_EGG)) {
                FastEgg.eggs.remove(random);
                FastEgg.plugin.getConfig().set("eggs." + random, null);
            } else {
                //is turtle egg
                //TODO

            }




        });
    }
}
