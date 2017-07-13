package net.warvale.vanquish.regions;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

/**
 * Created by Ron on 9/7/2017.
 */
public class RegionMapGen {
    @Getter @Setter private static int map[][] ={{},{}};
    @Getter @Setter public static double lavalevel = 20;

    public static void setMap(int[][] map) {
        RegionMapGen.map = map;
    }

    public static int[][] getMap() {
        return map;
    }

    public static void genRegionMap(World world) {
        double sizexz = world.getWorldBorder().getSize();
        System.out.println("Generating 2d world map, worldborder size: " + sizexz);
        for (double x=0; x < sizexz; x++) {
            for (double z=0; z < sizexz; z++) {
                Block currentBlock = world.getBlockAt(new Location(world, x,lavalevel,z));
                System.out.println("[DEBUG] [Vanquish] x"+x+"z"+z+". ");
                if (currentBlock.getType().equals(Material.LAVA)) {
                    // lava block
                    map[(int)x][(int)z] = 0;
                } else {
                    // non lava block
                    map[(int)x][(int)z] = 1;
                }

            }
        }
        System.out.println("Finished generating 2d world map: " + map);
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