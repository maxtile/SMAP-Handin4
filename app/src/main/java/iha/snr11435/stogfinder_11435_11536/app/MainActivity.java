package iha.snr11435.stogfinder_11435_11536.app;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements StationsFragment.OnFragmentInteractionListener, ButtonsFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.buttons_fragment,new ButtonsFragment())
                    .add(R.id.list_fragment, new StationsFragment())
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(StationService.GET_STOG_STATIONS);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String id) {

    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<Stations.StationItem> stations =  intent.getParcelableArrayListExtra(StationService.STOG_STATIONS_TAG);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.list_fragment, new StationsFragment(stations))
                    .commit();
        }
    };

    @Override
    public void onUpdateButtonClick() {
        //Todo: Start the service.
        Intent serviceIntent = new Intent(this, StationService.class);
        startService(serviceIntent);
    }

    @Override
    public void onFilterUpdated(CharSequence sequence) {
        StationsFragment.adapter.getFilter().filter(sequence);
    }

}
