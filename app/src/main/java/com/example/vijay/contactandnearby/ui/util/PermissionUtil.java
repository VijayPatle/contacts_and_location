package com.example.vijay.contactandnearby.ui.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.vijay.contactandnearby.ui.view.fragments.ContactFragment;

import java.lang.ref.WeakReference;

public class PermissionUtil {
    private static final String TAG =PermissionUtil.class.getSimpleName() ;

    private static PermissionUtil singleton = new PermissionUtil( );
    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private PermissionUtil() { }

    /* Static 'instance' method */
    public static PermissionUtil getInstance( ) {
        return singleton;
    }
    public boolean checkPermission(String permission,Context context) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }
    public void showPhonePermissionDialog(ContactFragment fragment, int code){
        fragment.requestPermissions( new String[]{Manifest.permission.CALL_PHONE}, code);

    }
    public void showContactPermissionDialog(ContactFragment fragment,int code){
        fragment.requestPermissions( new String[]{Manifest.permission.READ_CONTACTS}, code);

    }
    public void showSMSPermissionDialog(ContactFragment fragment, int code){
        fragment.requestPermissions(
                        new String[]{Manifest.permission.SEND_SMS},
                        code);

        }


}
