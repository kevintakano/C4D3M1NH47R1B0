package com.jkl.cademinhatribo;

import android.content.Intent;
import android.location.LocationManager;
import android.location.*;

//import com.google.android.gms.location.*;


import android.location.LocationProvider;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    private LocationListener mylocationlistener=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        setContentView(R.layout.activity_main);





    }

    public void onClickButton(View v)
    {
        try
        {
            LocationManager lManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            LocationListener lListener = new LocationListener() {
                public void onLocationChanged(Location locat) {
                    updateView(locat);
                }
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    //final String tvTxt = textView.getText().toString();
                    switch (status) {
                        case LocationProvider.AVAILABLE:
                            System.out.println( "Network location available again\n");
                            break;
                        case LocationProvider.OUT_OF_SERVICE:
                            System.out.println( "Network location out of service\n");
                            break;
                        case LocationProvider.TEMPORARILY_UNAVAILABLE:
                            System.out.println("Network location temporarily unavailable\n");
                            break;
                    }
                }
                public void onProviderEnabled(String provider) {}

                public void onProviderDisabled(String provider) {}

            };

            Log.v("MainActivity", "lListener:" + lListener);

            lManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, lListener);
            Log.v("Network", "Network");

            Double latitude, longitude;
            Log.v("LManager!: ","" + lManager);

            if (lManager != null) {
                Location location = lManager
                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    Log.v("Latlng","latlng: " + latitude + longitude);
                    Intent intent = new Intent(this, MapsActivity.class);
                    intent.putExtra("lat",latitude);
                    intent.putExtra("lon",longitude);
                    startActivity(intent);
                }

            }
        }catch(Exception e)
        {
            Log.v("Erro!: ","Erro");

        }


    }

    /*protected void createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }*/
    public void updateView(Location locat){
        Double latitude = locat.getLatitude();
        Double longitude = locat.getLongitude();
        System.out.println("lat: " + latitude + "lgn" + longitude);
        Log.v("UpdatView","" + latitude + longitude);
        //edLatitude.setText(latitude.toString());
        //edLongitude.setText(longitude.toString());
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



