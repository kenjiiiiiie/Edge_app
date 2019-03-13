package app.helianthus.edge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

public class ActivitySplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                boolean previouslyStarted = prefs.getBoolean(getString(R.string.first_launch), false);
                if (!previouslyStarted) {
                    SharedPreferences.Editor edit = prefs.edit();
                    edit.putBoolean(getString(R.string.first_launch), Boolean.TRUE).apply();
                    ActivitySplashScreen.this.startActivity(new Intent(ActivitySplashScreen.this, ActivityOnboarding.class));
                }
                else{
                    ActivitySplashScreen.this.startActivity(new Intent(ActivitySplashScreen.this, MainActivity.class));
                    ActivitySplashScreen.this.finish();
                }
            }
        }, 1000);
    }
}
