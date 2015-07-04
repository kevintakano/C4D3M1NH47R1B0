package com.jkl.cademinhatribo;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by kevintakano on 04/07/15.
 */
public class MyLocationListener implements LocationListener{
    // Define a listener that responds to location updates

        public void onLocationChanged(Location location) {

            // Called when a new location is found by the network location provider.

            System.out.println("Localização: " + location.getLatitude() + "," + location.getLongitude());
        }



    public void onStatusChanged(String provider, int status, Bundle extras) {}

        public void onProviderEnabled(String provider) {}

        public void onProviderDisabled(String provider) {}
    };

}
