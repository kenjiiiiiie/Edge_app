package app.helianthus.edge;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

public class On_Boarding extends AppCompatActivity implements View.OnClickListener{

    private ViewPager mViewPager;
    private int[] layouts = {R.layout.fragment_onboarding_01, R.layout.fragment_onboarding_02, R.layout.fragment_onboarding_03, R.layout.fragment_onboarding_04};
    private PageAdapter pageAdapter;

    private LinearLayout dots_layout;
    private ImageView[] dots;

    private Button btnSkip, btnFinish, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        mViewPager = findViewById(R.id.viewPager);
        pageAdapter = new PageAdapter(layouts, this);
        mViewPager.setAdapter(pageAdapter);

        dots_layout = findViewById(R.id .dotsLayout);
        createDots(0);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
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

        btnNext = findViewById(R.id.btnNext);
        btnSkip = findViewById(R.id.btnSkip);
        btnFinish = findViewById(R.id.btnFinish);
        btnNext.setOnClickListener(this);
        btnSkip.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

    }

    private void createDots(int current_position){
        if(dots_layout != null){
            dots_layout.removeAllViews();
        }

        dots = new ImageView[layouts.length];

        for(int i = 0; i<layouts.length; i++){
            dots[i] = new ImageView(this);
            if(i == current_position)
            {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dot));
            }
            else
            {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactive_dot));
            }

            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            param.setMargins(6,0,6,0);
            dots_layout.addView(dots[i], param);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnNext:
                loadNextSlide();
                break;

            case R.id.btnSkip:
                loadHome();
                break;
            case R.id.btnFinish:
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
