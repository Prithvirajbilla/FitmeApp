package in.fitbilla.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.felipecsl.gifimageview.library.GifImageView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.fitbilla.FitMeApplication;
import in.fitbilla.R;
import in.fitbilla.SelectDimensionActivity;
import in.fitbilla.core.UserProfile;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class SplashActivity extends BaseActivity {

    public static final int ANIMATION_SMOOTHENING_DELAY = 500;

    AccessToken accessTokenFB;

    CallbackManager callbackManager;

    @Bind(R.id.imageView)
    GifImageView imageView;

    @Bind(R.id.login_button)
    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        callbackManager = CallbackManager.Factory.create();
        // If the access token is available already assign it.
        loginButton.setVisibility(GONE);
        accessTokenFB = AccessToken.getCurrentAccessToken();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                doNavigate();
            }
        }, ANIMATION_SMOOTHENING_DELAY);
    }

    private void doNavigate() {
        if (accessTokenFB == null) {
            //implement email based auth token logic
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    accessTokenFB = loginResult.getAccessToken();
                    UserProfile profile = new UserProfile();
                    profile.setUserId(1);
                    FitMeApplication.setUserProfileToPref(profile, SplashActivity.this);
                    Intent intent = new Intent(SplashActivity.this, SelectDimensionActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onCancel() {

                }

                @Override
                public void onError(FacebookException error) {

                }
            });
            loginButton.setVisibility(VISIBLE);
        } else {
            Log.d("Splash Activity", "else block");
            SharedPreferences sharedPref = FitMeApplication.getSharedPreferences(this);
            String s = sharedPref.getString(getString(R.string.key_user), null);
            Log.d("Splash Activity", "IME" + s);
            if (s == null) {
                loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        accessTokenFB = loginResult.getAccessToken();
                        UserProfile profile = new UserProfile();
                        profile.setUserId(1);
                        FitMeApplication.setUserProfileToPref(profile, SplashActivity.this);
                        Intent intent = new Intent(SplashActivity.this, SelectDimensionActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });
                loginButton.setVisibility(VISIBLE);
            } else {
                final FitMeApplication globalContext = (FitMeApplication) getApplicationContext();
                Gson gson = new GsonBuilder().create();
                UserProfile profile = gson.fromJson(s, UserProfile.class);
                globalContext.setProfile(profile);
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        imageView.startAnimation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        imageView.stopAnimation();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(callbackManager!=null) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
}
