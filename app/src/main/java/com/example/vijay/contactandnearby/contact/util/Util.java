package com.example.vijay.contactandnearby.contact.util;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.location.LocationManager;

import static android.content.Context.LOCATION_SERVICE;

public class Util {
    /**
     * Returns true if this is a foreground service.
     *
     * @param context The {@link Context}.
     */
    public static boolean serviceIsRunningInForeground(Context context,Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(
                Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(
                Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                if (service.foreground) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isGPSEnabled(Context context){
        LocationManager service = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        return service.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
