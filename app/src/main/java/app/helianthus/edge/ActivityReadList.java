package app.helianthus.edge;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityReadList extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;

    RecyclerView recyclerView;
    private ListAdapter adapter;
    private ListData[] listData;

    static String EXTRA_CHOSEN_READS;
    Bitmap bitmap;
    TextView toolbarTitle;
    ImageView toolbarImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_read_list);

        View view = findViewById(R.id.reads_list_root);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }

        Toolbar toolbar = findViewById(R.id.reads_stress_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_w);

        toolbarTitle = findViewById(R.id.reads_list_toolbar_title);
        toolbarImg = findViewById(R.id.reads_list_toolbar_image);

        Window window = this.getWindow();

        switch (EXTRA_CHOSEN_READS) {
            case "Stress" :
                bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.img_stress);
                toolbarTitle.setText(getString(R.string.reads_title_stress));
                toolbarImg.setImageDrawable(getDrawable(R.drawable.img_stress));
                listData = new ListData[] {
                        new ListData("1", getString(R.string.stress_01_title)),
                        new ListData("2", getString(R.string.stress_02_title)),
                        new ListData("3", getString(R.string.stress_03_title)),
                        new ListData("4", getString(R.string.stress_04_title)),
                        new ListData("5", getString(R.string.stress_05_title)),
                        new ListData("6", getString(R.string.stress_06_title)),
                        new ListData("7", getString(R.string.stress_07_title)),
                        new ListData("8", getString(R.string.stress_08_title)),
                        new ListData("9", getString(R.string.stress_09_title)),
                        new ListData("10", getString(R.string.stress_10_title)),
                        new ListData("11", getString(R.string.stress_11_title)),
                        new ListData("12", getString(R.string.stress_12_title)),
                        new ListData("13", getString(R.string.stress_13_title)),
                        new ListData("14", getString(R.string.stress_14_title)),
                        new ListData("15", getString(R.string.stress_15_title)),
                        new ListData("16", getString(R.string.stress_16_title))
                };
                break;
            case "Anxiety" :
                bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.img_anxiety);
                toolbarTitle.setText(getString(R.string.reads_title_anxiety));
                toolbarImg.setImageDrawable(getDrawable(R.drawable.img_anxiety));
                listData = new ListData[] {
                        new ListData("1", getString(R.string.anxiety_01_title)),
                        new ListData("2", getString(R.string.anxiety_02_title)),
                        new ListData("3", getString(R.string.anxiety_03_title)),
                        new ListData("4", getString(R.string.anxiety_04_title)),
                        new ListData("5", getString(R.string.anxiety_05_title)),
                        new ListData("6", getString(R.string.anxiety_06_title)),
                        new ListData("7", getString(R.string.anxiety_07_title)),
                        new ListData("8", getString(R.string.anxiety_08_title)),
                };
                break;
            case "Depression" :
                bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.img_depression);
                toolbarTitle.setText(getString(R.string.reads_title_depression));
                toolbarImg.setImageDrawable(getDrawable(R.drawable.img_depression));
                listData = new ListData[] {
                        new ListData("1", getString(R.string.depression_01_title)),
                        new ListData("2", getString(R.string.depression_02_title)),
                        new ListData("3", getString(R.string.depression_03_title)),
                        new ListData("4", getString(R.string.depression_04_title)),
                        new ListData("5", getString(R.string.depression_05_title)),
                        new ListData("6", getString(R.string.depression_06_title)),
                        new ListData("7", getString(R.string.depression_07_title)),
                        new ListData("8", getString(R.string.depression_08_title)),
                };
                break;
        }

        Palette palette = Palette.from(bitmap).generate();
        collapsingToolbarLayout = findViewById(R.id.reads_collapsing_toolbar);

        Palette.Swatch extractedPalette = palette.getMutedSwatch();
        if(extractedPalette != null) {
            collapsingToolbarLayout.setContentScrimColor(extractedPalette.getRgb());
            window.setStatusBarColor(extractedPalette.getRgb());
        }

        recyclerView = findViewById(R.id.reads_stress_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new ListAdapter(listData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }
}
