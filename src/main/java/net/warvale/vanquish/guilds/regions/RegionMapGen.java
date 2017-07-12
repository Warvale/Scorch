package net.warvale.vanquish.guilds.regions;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Ron on 9/7/2017.
 */
public class RegionMapGen {
    private static ArrayList map = new ArrayList<>();
    public static double lavalevel = 20;

    public static void setMap(ArrayList map) {
        RegionMapGen.map = map;
    }

    public static ArrayList getMap() {
        return map;
    }

    public static void genRegionMap(World world) {

        double sizexz = world.getWorldBorder().getSize();
        System.out.println("Generating 2d world map, worldborder size: " + sizexz);
        for (double x=0; x <= sizexz; x++) {
            for (double z=0; z <= sizexz; z++) {
                Block currentBlock = world.getBlockAt(new Location(world, x,lavalevel,z));
                switch (currentBlock.getType()) {
                    case LAVA:
                        map.add(0);
                        break;

                    default: map.add(1);
                        break;
                }

            }
        }
        System.out.println("Finished generating 2d world map: " + map);
    }

    public static ArrayList getRegionMapFromFile(String path) throws IOException, ParseException {

        JSONParser parser = new JSONParser();


            Object obj = parser.parse(new FileReader(path));
            JSONObject jo = (JSONObject) obj;
            JSONArray omap = (JSONArray) jo.get("map");
            return omap;



    }

    public double getLavalevel() {
        return lavalevel;
    }

    public void setLavalevel(double lavalevel) {
        this.lavalevel = lavalevel;
    }

    public ArrayList<Integer> getRegionMap() {
        return map;
    }

    public void setRegionMap(ArrayList<Integer> map) {
        this.map = map;
    }


}
