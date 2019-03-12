package app.helianthus.edge;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class ActivitySplashScreen extends AppCompatActivity {

    //LottieAnimationView lottieAnimationView = findViewById(R.id.splash_edge_animation);

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
        }, 2500);

        /* lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {

                ActivitySplashScreen.this.startActivity(new Intent(ActivitySplashScreen.this, MainActivity.class));
                ActivitySplashScreen.this.finish();

            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        }); */

    }
}
