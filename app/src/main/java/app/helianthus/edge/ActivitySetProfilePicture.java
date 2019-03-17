package app.helianthus.edge;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ActivitySetProfilePicture extends AppCompatActivity {
    RoundedImageView avatar [];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile_picture);

        Toolbar mTopToolbar = findViewById(R.id.you_toolbar);
        mTopToolbar.inflateMenu(R.menu.you_app_bar_menu);

        avatar = new RoundedImageView[]{findViewById(R.id.avatar_001),findViewById(R.id.avatar_002),
                findViewById(R.id.avatar_003),findViewById(R.id.avatar_004),findViewById(R.id.avatar_005),
                findViewById(R.id.avatar_006),findViewById(R.id.avatar_007),findViewById(R.id.avatar_008),
                findViewById(R.id.avatar_009), findViewById(R.id.avatar_010), findViewById(R.id.avatar_011),
                findViewById(R.id.avatar_012)};

        for(int i = 0; i<=11; i++)
        {
            final int finalI = i;
            avatar[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityYou.profile_image_id = finalI;
                    finish();
                }
            });
        }

    }

}
