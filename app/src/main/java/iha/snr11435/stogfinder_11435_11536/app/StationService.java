package iha.snr11435.stogfinder_11435_11536.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class StationService extends Service {

    public static final String GET_STOG_STATIONS = "getStogStations";

    public StationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void getStations(){
        String result = null;
        URI myURI = null;

        try {
            myURI = new URI("http://stog.itog.dk/itog/action/list/format/json");
        } catch (URISyntaxException e) {
            // Deal with it
        }
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet getMethod = new HttpGet(myURI);
        HttpResponse webServerResponse = null;
        try {
            webServerResponse = httpClient.execute(getMethod);
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }

        HttpEntity httpEntity = webServerResponse.getEntity();

        if (httpEntity != null) {
            InputStream instream;
            try {
                instream = httpEntity.getContent();
                result = convertStreamToString(instream);
                instream.close();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        JSONObject myJSON = null;

        try {
            myJSON = new JSONObject(result);
            JSONArray array = myJSON.getJSONArray("items");
            for (int i = 0; i < array.length(); i++) {
                JSONObject stationJSON = array.getJSONObject(i);

                String name = stationJSON.getString("name");
                //Todo: Create array and intent here.
                Log.d("STOG", name);

            }
        } catch (JSONException e) {
        }

    }

    public String convertStreamToString(InputStream is) throws IOException {
        if (is != null) {
            StringBuilder sb = new StringBuilder();
            String line = null;

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } finally {
                is.close();
            }
            return sb.toString();
        } else {
            return "";
        }
    }


}
