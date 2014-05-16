package iha.snr11435.stogfinder_11435_11536.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxi on 15-05-14.
 */
public class Stations {

    public static List<StationItem> LIST = new ArrayList<StationItem>();

    private static void add(StationItem item){
        LIST.add(item);
    }

    static {
        add(new StationItem("1", "Station1"));
        add(new StationItem("2", "Station2"));
    }

    
    public static class StationItem {
        public String id;
        public String name;

        public StationItem(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
