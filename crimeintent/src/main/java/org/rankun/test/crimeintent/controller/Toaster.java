package org.rankun.test.crimeintent.controller;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by rankun203 on 12/10/14.
 */
public class Toaster {
    private static Toast toast;
    public static void toast(Context context, String msg) {
        if (null != toast) {
            toast.cancel();
        }

        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.show();
    }
}
