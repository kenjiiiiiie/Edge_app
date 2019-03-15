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
    FragmentTransaction fragmentTransaction;

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
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentChange(fragmentHome, "1");
                    return true;
                case R.id.navigation_journal:
                    fragmentChange(fragmentJournal, "2");
                    return true;
                case R.id.navigation_reads:
                    fragmentChange(fragmentReads, "2");
                    return true;
                case R.id.navigation_helplines:
                    fragmentChange(fragmentHelplines, "2");
                    return true;
            }
            return false;
        }
    };

    void fragmentChange(Fragment fragmentTo, String tag)
    {
        fragmentTransaction.setCustomAnimations(R.anim.anim_fragment_enter, R.anim.anim_fragment_exit);
        fragmentTransaction.replace(R.id.main_fragment_container, fragmentTo, tag).commit();
        fragmentActive = fragmentTo;
    }
}
