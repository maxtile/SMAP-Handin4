package iha.snr11435.stogfinder_11435_11536.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

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

    
    public static class StationItem implements Parcelable {
        public final String TAG = "StationItemClass";
        public String id;
        public String name;

        public StationItem(Parcel source){
            Log.d(TAG, "Deserializing parcel");
            this.id = source.readString();
            this.name = source.readString();
        }

        public StationItem(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            Log.d(TAG, "Write to parcel "  + i);
            parcel.writeString(id);
            parcel.writeString(name);
        }

        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
            public StationItem createFromParcel(Parcel in) {
                return new StationItem(in);
            }

            public StationItem[] newArray(int size) {
                return new StationItem[size];
            }
        };


    }
}
