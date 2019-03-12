package app.helianthus.edge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ActivitySplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ActivitySplashScreen.this.startActivity(new Intent(ActivitySplashScreen.this, MainActivity.class));
                ActivitySplashScreen.this.finish();
            }
        }, 1000);
    }
}
