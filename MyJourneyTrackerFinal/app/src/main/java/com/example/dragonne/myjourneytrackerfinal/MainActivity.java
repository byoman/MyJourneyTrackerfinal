package com.example.dragonne.myjourneytrackerfinal;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Location> locs;
    private Button butt;
    private boolean useGPS;
    private String button;
    private LocationManager lm;
    private LocationListener ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = "Start GPS";
        butt = (Button) findViewById(R.id.Buttgps);
        butt.setText(button);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useGPS = !useGPS;
                if (button == "Start GPS") {
                    button = "Stop GPS";
                    butt.setText(button);
                } else {
                    button = "Start GPS";
                    butt.setText(button);
                }
                if (useGPS) {
                    start();
                } else {
                    stop();
                }
            }
        });
        lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        ls = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                locs.add(location);
                if(locs.size()>100){
                    locs.remove(0);
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        try{
            lm.requestLocationUpdates(lm.GPS_PROVIDER,1000,0,ls);
        } catch(SecurityException e) {
            Log.e("GPS",e.getMessage());
        }
    }
    private void start(){

    }
    private void stop(){

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
