package app.helianthus.edge;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
