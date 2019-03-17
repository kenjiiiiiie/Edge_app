package app.helianthus.edge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.makeramen.roundedimageview.RoundedImageView;

public class ActivityYou extends AppCompatActivity {
    EditText you_name, you_age;
    RoundedImageView imageView;
    SharedPreferences prefs;
    SharedPreferences.Editor edit;
    MaterialButton changeProfile;
    int image_id;

    static boolean from_setProfile = false;

    static int profile_image_id;
    int drawable_avatar_id[] = {R.drawable.ic_avatar_001, R.drawable.ic_avatar_002, R.drawable.ic_avatar_003,
            R.drawable.ic_avatar_004,R.drawable.ic_avatar_005, R.drawable.ic_avatar_006, R.drawable.ic_avatar_007,
            R.drawable.ic_avatar_008, R.drawable.ic_avatar_009, R.drawable.ic_avatar_001, R.drawable.ic_avatar_011,
            R.drawable.ic_avatar_012};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you);

        changeProfile = findViewById(R.id.btnChangeProfile);
        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityYou.this, ActivitySetProfilePicture.class));
                from_setProfile = true;
            }
        });

        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        edit = prefs.edit();

        Toolbar mTopToolbar = findViewById(R.id.you_toolbar);
        mTopToolbar.inflateMenu(R.menu.you_app_bar_menu);
        mTopToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.you_save:
                        //Save journal
                        int user_age = Integer.parseInt(you_age.getText().toString());
                        if(user_age >= 14 && user_age <= 100)
                        {
                            edit.putInt(getString(R.string.image_id), drawable_avatar_id[profile_image_id]).apply();
                            edit.putString(getString(R.string.name), you_name.getText().toString()).apply();
                            edit.putString(getString(R.string.age), you_age.getText().toString()).apply();
                            Toast.makeText(ActivityYou.this, "Saved!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(ActivityYou.this, "Age should between 14 to 100", Toast.LENGTH_SHORT).show();
                        }
                    default:
                        return false;
                }
            }
        });


        String name = prefs.getString(getString(R.string.name), "name");
        String age = prefs.getString(getString(R.string.age), "0");
        image_id = prefs.getInt(getString(R.string.image_id), 0);

        imageView = findViewById(R.id.RoundedImageView);

        switch (image_id)
        {
            case R.drawable.ic_avatar_001:
                imageView.setImageResource(R.drawable.ic_avatar_001);
                break;
            case R.drawable.ic_avatar_002:
                imageView.setImageResource(R.drawable.ic_avatar_002);
                break;
            case R.drawable.ic_avatar_003:
                imageView.setImageResource(R.drawable.ic_avatar_003);
                break;
            case R.drawable.ic_avatar_004:
                imageView.setImageResource(R.drawable.ic_avatar_004);
                break;
            case R.drawable.ic_avatar_005:
                imageView.setImageResource(R.drawable.ic_avatar_005);
                break;
            case R.drawable.ic_avatar_006:
                imageView.setImageResource(R.drawable.ic_avatar_006);
                break;
            case R.drawable.ic_avatar_007:
                imageView.setImageResource(R.drawable.ic_avatar_007);
                break;
            case R.drawable.ic_avatar_008:
                imageView.setImageResource(R.drawable.ic_avatar_008);
                break;
            case R.drawable.ic_avatar_009:
                imageView.setImageResource(R.drawable.ic_avatar_009);
                break;
            case R.drawable.ic_avatar_010:
                imageView.setImageResource(R.drawable.ic_avatar_010);
                break;
            case R.drawable.ic_avatar_011:
                imageView.setImageResource(R.drawable.ic_avatar_011);
                break;
            case R.drawable.ic_avatar_012:
                imageView.setImageResource(R.drawable.ic_avatar_012);
                break;
        }

        you_name = findViewById(R.id.name);
        you_age = findViewById(R.id.age);

        you_name.setText(name);
        you_age.setText(age);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(from_setProfile)
        {
            switch (profile_image_id)
            {
                case 0:
                    imageView.setImageResource(R.drawable.ic_avatar_001);
                    break;
                case 1:
                    imageView.setImageResource(R.drawable.ic_avatar_002);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.ic_avatar_003);
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.ic_avatar_004);
                    break;
                case 4:
                    imageView.setImageResource(R.drawable.ic_avatar_005);
                    break;
                case 5:
                    imageView.setImageResource(R.drawable.ic_avatar_006);
                    break;
                case 6:
                    imageView.setImageResource(R.drawable.ic_avatar_007);
                    break;
                case 7:
                    imageView.setImageResource(R.drawable.ic_avatar_008);
                    break;
                case 8:
                    imageView.setImageResource(R.drawable.ic_avatar_009);
                    break;
                case 9:
                    imageView.setImageResource(R.drawable.ic_avatar_010);
                    break;
                case 10:
                    imageView.setImageResource(R.drawable.ic_avatar_011);
                    break;
                case 11:
                    imageView.setImageResource(R.drawable.ic_avatar_012);
                    break;
            }
            from_setProfile = false;
        }
    }
}
