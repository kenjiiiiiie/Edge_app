package app.helianthus.edge;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitySplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        View view = findViewById(R.id.splash_root);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }

        LottieAnimationView animSplash = findViewById(R.id.splash_edge_animation);
        animSplash.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.e("Animation:", "Start");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e("Animation:", "End");

                try {
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
                catch (Exception ex) {
                    ex.toString();
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.e("Animation:", "Cancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.e("Animation:", "Repeat");
            }
        });
    }
}
