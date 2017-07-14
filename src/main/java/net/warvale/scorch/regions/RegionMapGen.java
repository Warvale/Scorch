package net.warvale.scorch.regions;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Map;

/**
 * Created by Ron on 9/7/2017.
 */
public class RegionMapGen {
    @Getter @Setter private static String map[][] = new String[1][1];
    @Getter @Setter private static double lavalevel = 20;
    private static Map playerLocations;


    public static Map getPlayerLocations() {
        return playerLocations;
    }

    public static void setPlayerLocations(Map playerLocations) {
        RegionMapGen.playerLocations = playerLocations;
    }

    public static void setMap(String[][] map) {
        RegionMapGen.map = map;
    }

    public static String[][] getMap() {
        return map;
    }

    public static void genRegionMap(World world) {
        double sizexz = world.getWorldBorder().getSize();
        map = new String[(int)sizexz][(int)sizexz];
        System.out.println("Generating 2d world map, worldborder size: " + sizexz);
        for (double x=0; x < sizexz; x++) {
            for (double z=0; z < sizexz; z++) {
                Block currentBlock = world.getBlockAt(new Location(world, x,lavalevel,z));

                if (currentBlock.getType().equals(Material.LAVA) ||  currentBlock.getType().equals(Material.STATIONARY_LAVA)) {
                    // lava block
                    map[(int)x][(int)z] = "lava";
                } else {
                    // non lava block
                    map[(int)x][(int)z] = "unknown";
                }

            }
        }


        System.out.println("Finished generating 2d world map: " + map.toString());
    }

//    public static int[][] getRegionMapFromFile(String path) throws IOException, ParseException {
//
//        JSONParser parser = new JSONParser();
//TODO: Make getRegionMapFromFile(path) work with the new changes.
//            Commented out for now.
//        Object obj = parser.parse(new FileReader(path));
//        JSONObject jo = (JSONObject) obj;
//        JSONArray omap = (JSONArray) jo.get("map");
//        return omap;
//
//
//
//    }




}
