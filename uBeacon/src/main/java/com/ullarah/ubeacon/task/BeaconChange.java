package com.ullarah.ubeacon.task;

import com.ullarah.ubeacon.BeaconInit;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.List;
import java.util.Random;

public class BeaconChange {

    private static final long beaconInterval = BeaconInit.getPlugin().getConfig().getLong("interval") * 20;

    @SuppressWarnings("deprecation")
    public static void task() {

        Bukkit.getServer().getScheduler().runTaskTimer(BeaconInit.getPlugin(), () -> {

            List<String> beaconList = BeaconInit.getPlugin().getConfig().getStringList("beacons");

            for (String beacon : beaconList) {

                String[] beaconParts = beacon.split("\\|");

                World world = Bukkit.getWorld(beaconParts[1]);

                Location location = new Location(world,
                        Integer.parseInt(beaconParts[2]),
                        Integer.parseInt(beaconParts[3]) + 1,
                        Integer.parseInt(beaconParts[4]));

                world.getBlockAt(location).setType(Material.STAINED_GLASS);
                world.getBlockAt(location).setData((byte) new Random().nextInt(6)); // Is there another way?

            }

        }, beaconInterval, beaconInterval);

    }

}
