package in.fitbilla;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Created by prateek on 2/28/16.
 */
public class GCMHandler {
    private static final String TAG = GCMHandler.class.getSimpleName();


    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private static boolean checkPlayServices(Context ctx) {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(ctx);
        if (resultCode != ConnectionResult.SUCCESS) {
            Log.i(TAG, "This device is not supported.");
            return false;
        }
        return true;

    }

    public static void registerToken(final Context ctx) {
        try {
            if (checkPlayServices(ctx)) {
                // Start IntentService to register this application with GCM.
                Log.d(TAG, "Checked google play services");
                Intent intent = new Intent(ctx, RegistrationService.class);
                Log.d(TAG, "Calling RegistrationIntentService");
                ctx.startService(intent);
                Log.d(TAG, "Done calling RegistrationIntentService");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
