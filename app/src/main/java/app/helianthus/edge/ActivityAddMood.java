package app.helianthus.edge;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.xw.repo.BubbleSeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ActivityAddMood extends AppCompatActivity {

    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";

    View rootLayout;

    private int revealX;
    private int revealY;
    float moodProgress = 0.50f;
    TextView moodLevel;

    MaterialButton btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mood);

        View view = findViewById(R.id.add_mood_root);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }

        final Intent intent = getIntent();
        rootLayout = findViewById(R.id.add_mood_root);

        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) && intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)) {
            rootLayout.setVisibility(View.INVISIBLE);

            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0);
            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0);

            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        revealActivity(revealX, revealY);
                        rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
        }
        else {
            rootLayout.setVisibility(View.VISIBLE);
        }

        Toolbar toolbar = findViewById(R.id.add_mood_toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.journal_write_app_bar_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_cancel);

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.dimAmount = 0.42f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(layoutParams);

        final LottieAnimationView moodAnimation = findViewById(R.id.add_mood_animation);

        BubbleSeekBar moodSeekBar = findViewById(R.id.add_mood_seek_bar);
        moodLevel = findViewById(R.id.add_mood_level_text);
        moodSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {

                moodAnimation.setProgress(progressFloat / 100f);
                moodProgress = progressFloat / 100f;

                if (moodProgress >= 0.875f) {
                    moodLevel.setText(getString(R.string.add_mood_level_text_005));
                }
                else if (moodProgress >= 0.625f) {
                    moodLevel.setText(getString(R.string.add_mood_level_text_004));
                }
                else if (moodProgress >= 0.375f) {
                    moodLevel.setText(getString(R.string.add_mood_level_text_003));
                }
                else if (moodProgress >= 0.125f) {
                    moodLevel.setText(getString(R.string.add_mood_level_text_002));
                }
                else if (moodProgress >= 0.0f) {
                    moodLevel.setText(getString(R.string.add_mood_level_text_001));
                }

            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {

            }
        });

        btnDone = findViewById(R.id.add_mood_fab_done);
        btnDone.setOnClickListener(v -> {

            moodSeekBar.setEnabled(false);

            if (moodProgress >= 0.875) {
                moodAnimation.setAnimation(R.raw.mood_005_animation);
            }
            else if (moodProgress >= 0.625) {
                moodAnimation.setAnimation(R.raw.mood_004_animation);
            }
            else if (moodProgress >= 0.375) {
                moodAnimation.setAnimation(R.raw.mood_003_animation);
            }
            else if (moodProgress >= 0.125) {
                moodAnimation.setAnimation(R.raw.mood_002_animation);
            }
            else if (moodProgress >= 0.0) {
                moodAnimation.setAnimation(R.raw.mood_001_animation);
            }

            moodAnimation.playAnimation();
            moodAnimation.addAnimatorListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) { }
                @Override
                public void onAnimationEnd(Animator animation) {
                    unRevealActivity();
                }
                @Override
                public void onAnimationCancel(Animator animation) { }

                @Override
                public void onAnimationRepeat(Animator animation) { }
            });

        });

    }

    protected void revealActivity(int x, int y) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);

            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0, finalRadius);
            circularReveal.setDuration(500);
            circularReveal.setInterpolator(new AccelerateDecelerateInterpolator());

            rootLayout.setVisibility(View.VISIBLE);

            overridePendingTransition(0,0);
            circularReveal.start();
        }
        else {
            finish();
        }
    }

    protected void unRevealActivity() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            finish();
        }
        else {
            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, revealX, revealY, finalRadius, 0);
            circularReveal.setDuration(300);
            circularReveal.setInterpolator(new AccelerateInterpolator());
            circularReveal.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    rootLayout.setVisibility(View.INVISIBLE);
                    finish();
                }
            });

            overridePendingTransition(0,0);
            circularReveal.start();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                unRevealActivity();
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        unRevealActivity();
    }

}
