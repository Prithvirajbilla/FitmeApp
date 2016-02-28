package in.fitbilla;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.FacebookSdk;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import in.fitbilla.core.UserProfile;

public class FitMeApplication extends Application {

    private static final String prefKey = "in.adoro.app.PREFERENCE_FILE_KEY";

    private UserProfile profile;

    @Override
    public void onCreate() {
        super.onCreate();
        initialize();
    }

    private void initialize() {
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    public UserProfile getProfile() {
        if (null != profile && profile.getUserId() != 0)
            return profile;
        return getUserProfileFromPref(this);
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
        setUserProfileToPref(profile, this);
    }

    public static UserProfile getUserProfileFromPref(Context context) {
        SharedPreferences store = getSharedPreferences(context);
        String str = store.getString(context.getString(R.string.key_user), "");
        if (str.equals("")) {
            return null;
        }
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(str, UserProfile.class);
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(prefKey, Context.MODE_PRIVATE);
    }

    public static void setUserProfileToPref(UserProfile profile, Context context) {
        try {
            if (context != null) {
                SharedPreferences store = getSharedPreferences(context);
                SharedPreferences.Editor editor = store.edit();
                Gson gson = new GsonBuilder().create();
                editor.putString(context.getString(R.string.key_user), gson.toJson(profile)).apply();
            }
        }catch (Exception e) {
            ///skip
        }
    }


}
