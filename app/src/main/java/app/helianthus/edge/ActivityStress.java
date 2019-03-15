package app.helianthus.edge;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.DividerItemDecoration;
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
        };

        recyclerView = findViewById(R.id.reads_stress_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new ListAdapter(listData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }

}
