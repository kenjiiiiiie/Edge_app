package app.helianthus.edge;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment fragmentHome = new FragmentHome();
    Fragment fragmentJournal = new FragmentJournal();
    Fragment fragmentReads = new FragmentReads();
    Fragment fragmentHelplines = new FragmentHelplines();
    Fragment fragmentActive = fragmentHome;

    FloatingActionButton fabAddMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = findViewById(R.id.main_root);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }

        fragmentManager.beginTransaction().add(R.id.main_fragment_container, fragmentHome, "1").commit();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fabAddMood = findViewById(R.id.home_fab_add_mood);
        fabAddMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presentActivity(v);
            }
        });

    }

    private void presentActivity(View view) {

        /* final View dialogAddMood = View.inflate(this, R.layout.activity_add_mood, null);

        final Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogAddMood); */

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, "transition");

        int revealX = (int) (view.getX() + view.getWidth() / 2);
        int revealY = (int) (view.getY() + view.getHeight() / 2);

        Intent intent = new Intent(this, ActivityAddMood.class);
        intent.putExtra(ActivityAddMood.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(ActivityAddMood.EXTRA_CIRCULAR_REVEAL_Y, revealY);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        ActivityCompat.startActivity(this, intent, options.toBundle());
        overridePendingTransition(R.anim.anim_slide_up,R.anim.anim_slide_down);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentTransaction fragmentTransaction;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.anim_fragment_enter, R.anim.anim_fragment_exit);
                    fragmentTransaction.replace(R.id.main_fragment_container, fragmentHome, "1").commit();
                    fragmentActive = fragmentHome;
                    return true;
                case R.id.navigation_journal:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.anim_fragment_enter, R.anim.anim_fragment_exit);
                    fragmentTransaction.replace(R.id.main_fragment_container, fragmentJournal, "2").commit();
                    fragmentActive = fragmentJournal;
                    return true;
                case R.id.navigation_reads:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.anim_fragment_enter, R.anim.anim_fragment_exit);
                    fragmentTransaction.replace(R.id.main_fragment_container, fragmentReads, "3").commit();
                    fragmentActive = fragmentReads;
                    return true;
                case R.id.navigation_helplines:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.anim_fragment_enter, R.anim.anim_fragment_exit);
                    fragmentTransaction.replace(R.id.main_fragment_container, fragmentHelplines, "4").commit();
                    fragmentActive = fragmentHelplines;
                    return true;
            }
            return false;
        }
    };

}
