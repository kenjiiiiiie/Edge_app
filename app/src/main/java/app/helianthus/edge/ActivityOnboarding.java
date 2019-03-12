package app.helianthus.edge;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.novoda.spritz.Spritz;
import com.novoda.spritz.SpritzStep;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

public class ActivityOnboarding extends AppCompatActivity implements View.OnClickListener{

    private ViewPager mViewPager;
    private int[] layouts = {R.layout.fragment_onboarding_01, R.layout.fragment_onboarding_02, R.layout.fragment_onboarding_03, R.layout.fragment_onboarding_04};
    private PageAdapter pageAdapter;
    private WormDotsIndicator wormDotsIndicator;

    //private LinearLayout dots_layout;
    private ImageView[] dots;

    private Button btnSkip, btnFinish, btnNext;
    private Spritz spritz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        mViewPager = findViewById(R.id.onboarding_view_pager);
        pageAdapter = new PageAdapter(layouts, this);
        mViewPager.setAdapter(pageAdapter);

        wormDotsIndicator = findViewById(R.id.worm_dot);
        wormDotsIndicator.setViewPager(mViewPager);

        LottieAnimationView lottieAnimationView = findViewById(R.id.onboarding_animation);

        spritz = Spritz.with(lottieAnimationView)
                .withSteps(
                    new SpritzStep.Builder()
                        .withAutoPlayDuration(1000, TimeUnit.MILLISECONDS)
                        .withSwipeDuration(1000, TimeUnit.MILLISECONDS)
                        .build(),
                    new SpritzStep.Builder()
                        .withSwipeDuration(1000, TimeUnit.MILLISECONDS)
                        .build(),
                    new SpritzStep.Builder()
                        .withSwipeDuration(1000, TimeUnit.MILLISECONDS)
                        .build(),
                    new SpritzStep.Builder()
                        .withSwipeDuration(1000, TimeUnit.MILLISECONDS)
                        .build()
                )
                .build();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==layouts.length-1){
                    btnFinish.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.INVISIBLE);
                    btnSkip.setVisibility(View.INVISIBLE);
                }
                else{
                    btnFinish.setVisibility(View.INVISIBLE);
                    btnNext.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnNext = findViewById(R.id.onboarding_btn_next);
        btnSkip = findViewById(R.id.onboarding_btn_skip);
        btnFinish = findViewById(R.id.onboarding_btn_finish);
        btnNext.setOnClickListener(this);
        btnSkip.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

    }

    /*private void createDots(int current_position){
        if(dots_layout != null){
            dots_layout.removeAllViews();
        }

        dots = new ImageView[layouts.length];

        for(int i = 0; i<layouts.length; i++){
            dots[i] = new ImageView(this);
            if(i == current_position)
            {
               //dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dot));
            }
            else
            {
                //dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactive_dot));
            }

            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            param.setMargins(6,0,6,0);
           //dots_layout.addView(dots[i], param);
        }
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        spritz.attachTo(mViewPager);
        spritz.startPendingAnimations();
    }

    @Override
    protected void onStop() {
        spritz.detachFrom(mViewPager);
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.onboarding_btn_next:
                loadNextSlide();
                break;

            case R.id.onboarding_btn_skip:
                loadHome();
                break;

            case R.id.onboarding_btn_finish:
                loadHome();
                break;
        }
    }

    private void loadHome()
    {
        finish();
    }

    private void loadNextSlide()
    {
        try
        {
            int next_slide = mViewPager.getCurrentItem()+1;
            if(next_slide<layouts.length)
            {
                mViewPager.setCurrentItem(next_slide);
            }else {
                finish();
            }
        }
        catch(Exception e)
        {
            AlertDialog.Builder a = new AlertDialog.Builder(this);
            a.setMessage(String.valueOf(e.getMessage()));
            a.show();
        }
    }
}
