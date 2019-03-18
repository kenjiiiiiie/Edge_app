package app.helianthus.edge;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
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

        String stress_title[] = {getString(R.string.stress_01_title), getString(R.string.stress_02_title), getString(R.string.stress_03_title),
                getString(R.string.stress_04_title), getString(R.string.stress_05_title), getString(R.string.stress_06_title),
                getString(R.string.stress_07_title), getString(R.string.stress_08_title), getString(R.string.stress_09_title),
                getString(R.string.stress_10_title), getString(R.string.stress_11_title), getString(R.string.stress_12_title),
                getString(R.string.stress_13_title), getString(R.string.stress_14_title), getString(R.string.stress_15_title),
                getString(R.string.stress_16_title) };
        String stress_content[] = {getString(R.string.stress_01_body), getString(R.string.stress_02_body), getString(R.string.stress_03_body),
                getString(R.string.stress_04_body), getString(R.string.stress_05_body), getString(R.string.stress_06_body),
                getString(R.string.stress_07_body), getString(R.string.stress_08_body), getString(R.string.stress_09_body),
                getString(R.string.stress_10_body), getString(R.string.stress_11_body), getString(R.string.stress_12_body),
                getString(R.string.stress_13_body), getString(R.string.stress_14_body), getString(R.string.stress_15_body),
                getString(R.string.stress_16_body)};
        String stress_cite[] = {getString(R.string.stress_01_cite), getString(R.string.stress_02_cite), getString(R.string.stress_03_cite),
                getString(R.string.stress_04_cite), getString(R.string.stress_05_cite), getString(R.string.stress_06_cite),
                getString(R.string.stress_07_cite), getString(R.string.stress_08_cite), getString(R.string.stress_09_cite),
                getString(R.string.stress_10_cite), getString(R.string.stress_11_cite), getString(R.string.stress_12_cite),
                getString(R.string.stress_13_cite), getString(R.string.stress_14_cite), getString(R.string.stress_15_cite),
                getString(R.string.stress_16_cite)};

        String anxiety_title[] = {getString(R.string.anxiety_01_title), getString(R.string.anxiety_02_title), getString(R.string.anxiety_03_title),
                getString(R.string.anxiety_04_title), getString(R.string.anxiety_05_title), getString(R.string.anxiety_06_title),
                getString(R.string.anxiety_07_title), getString(R.string.anxiety_08_title)};
        String anxiety_content[] = {getString(R.string.stress_01_body), getString(R.string.anxiety_02_body), getString(R.string.anxiety_03_body),
                getString(R.string.anxiety_04_body), getString(R.string.anxiety_05_body), getString(R.string.anxiety_06_body),
                getString(R.string.anxiety_07_body), getString(R.string.anxiety_08_body)};
        String anxiety_cite[] = {getString(R.string.anxiety_01_cite), getString(R.string.anxiety_02_cite), getString(R.string.anxiety_03_cite),
                getString(R.string.anxiety_04_cite), getString(R.string.anxiety_05_cite), getString(R.string.anxiety_06_cite),
                getString(R.string.anxiety_07_cite), getString(R.string.anxiety_08_cite)};

        String depression_title[] = {getString(R.string.depression_01_title), getString(R.string.depression_02_title), getString(R.string.depression_03_title),
                getString(R.string.depression_04_title), getString(R.string.depression_05_title), getString(R.string.depression_06_title),
                getString(R.string.depression_07_title), getString(R.string.depression_08_title)};
        String depression_content[] = {getString(R.string.stress_01_body), getString(R.string.depression_02_body), getString(R.string.depression_03_body),
                getString(R.string.depression_04_body), getString(R.string.depression_05_body), getString(R.string.depression_06_body),
                getString(R.string.depression_07_body), getString(R.string.depression_08_body)};
        String depression_cite[] = {getString(R.string.depression_01_cite), "", getString(R.string.depression_03_cite),
                getString(R.string.depression_04_cite), getString(R.string.depression_05_cite), getString(R.string.depression_06_cite),
                getString(R.string.depression_07_cite), getString(R.string.depression_08_cite)};

        ReadRecycleAdapter readRecycleAdapter;
        readRecycleAdapter = new ReadRecycleAdapter(stress_title, stress_content, stress_cite);

        switch (EXTRA_CHOSEN_READS) {
            case "Stress" :
                bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.img_stress);
                toolbarTitle.setText(getString(R.string.reads_title_stress));
                toolbarImg.setImageDrawable(getDrawable(R.drawable.img_stress));
                readRecycleAdapter = new ReadRecycleAdapter(stress_title, stress_content, stress_cite);
                break;
            case "Anxiety" :
                bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.img_anxiety);
                toolbarTitle.setText(getString(R.string.reads_title_anxiety));
                toolbarImg.setImageDrawable(getDrawable(R.drawable.img_anxiety));
                readRecycleAdapter = new ReadRecycleAdapter(anxiety_title, anxiety_content, anxiety_cite);
                break;
            case "Depression" :
                bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.img_depression);
                toolbarTitle.setText(getString(R.string.reads_title_depression));
                toolbarImg.setImageDrawable(getDrawable(R.drawable.img_depression));
                readRecycleAdapter = new ReadRecycleAdapter(depression_title, depression_content, depression_cite);
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
        recyclerView.setAdapter(readRecycleAdapter);
    }
}
