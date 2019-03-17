package app.helianthus.edge;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityStress extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;

    RecyclerView recyclerView;
    private ListAdapter adapter;
    private ListData[] listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stress);

        View view = findViewById(R.id.reads_stress_root);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }

        Toolbar toolbar = findViewById(R.id.reads_stress_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_w);

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.img_stress);
        Window window = this.getWindow();

        Palette palette = Palette.from(bitmap).generate();
        collapsingToolbarLayout = findViewById(R.id.reads_collapsing_toolbar);

        Palette.Swatch extractedPalette = palette.getMutedSwatch();
        if(extractedPalette != null) {
            collapsingToolbarLayout.setContentScrimColor(extractedPalette.getRgb());
            window.setStatusBarColor(extractedPalette.getRgb());
        }

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

        recyclerView = findViewById(R.id.reads_stress_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new ListAdapter(listData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }

}
