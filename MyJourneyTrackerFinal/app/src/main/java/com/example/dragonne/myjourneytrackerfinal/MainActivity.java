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
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Location> locs;
    public static int timecc;
    private Button butt;
    public static boolean useGPS;
    private String button,timemessage,avemess,curmess;
    private LocationManager lm;
    private LocationListener ls;
    public static TextView avespeed,curspeed,time,track;
    public static float ave; //ceasar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locs = new ArrayList<Location>();
        useGPS = false;
        button = "Start GPS";
        timemessage = "Time :";
        curmess = "Current Speed : ";
        avemess = "Average speed : ";
        final Graph graph = (Graph) findViewById(R.id.graph);
        timecc=0;
        curspeed = (TextView) findViewById(R.id.curspeed);
        avespeed = (TextView) findViewById(R.id.avespeed);
        time = (TextView) findViewById(R.id.time);
        track = (TextView) findViewById(R.id.track);
        butt = (Button) findViewById(R.id.Buttgps);
        butt.setText(button);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button == "Start GPS") {
                    track.setText("GPS Active");
                    useGPS = true;
                    button = "Stop GPS";
                    butt.setText(button);
                } else {
                    button = "Start GPS";
                    track.setText("GPS Inactive");
                    useGPS = false;
                    butt.setText(button);
                    timecc=0;
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
                    if(useGPS){
                        ave = average();
                        avespeed.setText(avemess+ave+"km/h");
                        curspeed.setText(curmess+(locs.get(locs.size()-1).getSpeed())*3.6f+"km/h");
                        timecc++;
                        time.setText(timemessage+timecc+"s");
                        graph.invalidate();
                    } else {
                        avespeed.setText(avemess+"N/A");
                        curspeed.setText(curmess+"N/A");
                        time.setText(timemessage+"N/A");
                        timecc = 0;
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
    public static float average(){
        float res = 0;
        if(locs.size()==0){
            return res;
        } else if( locs.size() == 1){
            return locs.get(0).getSpeed()*3.6f;
        } else {
            for(int i = 0; i<locs.size();i++){
                res+= (locs.get(i).getSpeed())*3.6f;
            }
            res /= locs.size();
            return res;
        }
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
