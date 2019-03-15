package app.helianthus.edge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;

public class ActivitySplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        View view = findViewById(R.id.splash_root);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                boolean previouslyStarted = prefs.getBoolean(getString(R.string.first_launch), false);
                if (!previouslyStarted) {
                    SharedPreferences.Editor edit = prefs.edit();
                    edit.putBoolean(getString(R.string.first_launch), Boolean.TRUE).apply();
                    ActivitySplashScreen.this.startActivity(new Intent(ActivitySplashScreen.this, ActivityOnboarding.class));
                    ActivitySplashScreen.this.finish();
                }
                else{
                    ActivitySplashScreen.this.startActivity(new Intent(ActivitySplashScreen.this, MainActivity.class));
                    ActivitySplashScreen.this.finish();
                }
            }
        }, 2000);
    }
}
