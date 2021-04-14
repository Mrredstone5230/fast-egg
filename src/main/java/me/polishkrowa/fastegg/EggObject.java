package me.polishkrowa.fastegg;

import org.bukkit.Location;

public class EggObject {

    public Location location;
    public int ticks;

    public EggObject(Location loc, int ticks) {
        this.location = loc;
        this.ticks = ticks;
    }
}
