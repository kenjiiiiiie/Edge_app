package app.helianthus.edge;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.Game2.Stress.Stress_Game;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

public class FragmentHome extends Fragment {
    private FragmentHomeViewModel mViewModel;
    private RecyclerView recyclerView;
    private ArrayList<GameModel> imageModelArrayList;
    private GameAdapter adapter;
    private MoodDBEntryHelper dbHelper ;
    private SQLiteDatabase database;

    static Context context;
    static int mood_count[] = new int[5];
    static int mood_id;

    BarChart barChart;

    private int[] myImageList = new int[]{R.drawable.img_game_stress, R.drawable.img_game_anxiety
            , R.drawable.img_game_depression};
    private String[] myImageNameList = new String[]{"Decide", "Coming Soon!!!", "Coming Soon!!!"};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getContext();
        Toolbar mTopToolbar = view.findViewById(R.id.home_toolbar);
        mTopToolbar.inflateMenu(R.menu.home_app_bar_menu);

        mTopToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_menu_profile:
                        getActivity().startActivity(new Intent(getContext(), ActivityYou.class));
                        return true;
                    default:
                        return false;
                }
            }
        });

        String date_n = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(new Date());
        TextView date = view.findViewById(R.id.home_date);
        date.setText(date_n);

        recyclerView  = view.findViewById(R.id.home_mini_games_recyclerview);
        imageModelArrayList = gameItems();
        adapter = new GameAdapter(getContext(), imageModelArrayList, myImageNameList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        barChart = view.findViewById(R.id.mood_bar_chart);

        dbHelper = new MoodDBEntryHelper(getContext());
        database = dbHelper.getReadableDatabase();

        String[] projection = {MoodCount._ID, MoodCount.COLUMN_MOOD_1, MoodCount.COLUMN_MOOD_2, MoodCount.COLUMN_MOOD_3, MoodCount.COLUMN_MOOD_4, MoodCount.COLUMN_MOOD_5 };
        String sortOrder = MoodCount._ID + " ASC";
        Cursor cursor = database.query(
                MoodCount.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        if(cursor.getCount() <= 0)
        {
            ContentValues values = new ContentValues();
            values.put(MoodCount.COLUMN_MOOD_1, 0);
            values.put(MoodCount.COLUMN_MOOD_2, 0);
            values.put(MoodCount.COLUMN_MOOD_3, 0);
            values.put(MoodCount.COLUMN_MOOD_4, 0);
            values.put(MoodCount.COLUMN_MOOD_5, 0);
            database.insert(MoodCount.TABLE_NAME, null, values);
        }

        while(cursor.moveToNext())
        {
            mood_id = cursor.getInt(cursor.getColumnIndexOrThrow(MoodCount._ID));
            mood_count[0] = cursor.getInt(cursor.getColumnIndexOrThrow(MoodCount.COLUMN_MOOD_1));
            mood_count[1] = cursor.getInt(cursor.getColumnIndexOrThrow(MoodCount.COLUMN_MOOD_2));
            mood_count[2] = cursor.getInt(cursor.getColumnIndexOrThrow(MoodCount.COLUMN_MOOD_3));
            mood_count[3] = cursor.getInt(cursor.getColumnIndexOrThrow(MoodCount.COLUMN_MOOD_4));
            mood_count[4] = cursor.getInt(cursor.getColumnIndexOrThrow(MoodCount.COLUMN_MOOD_5));
        }

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1f, (float) mood_count[0]));
        entries.add(new BarEntry(2f, (float) mood_count[1]));
        entries.add(new BarEntry(3f, (float) mood_count[2]));
        entries.add(new BarEntry(4f, (float) mood_count[3]));
        entries.add(new BarEntry(5f, (float) mood_count[4]));

        BarDataSet set = new BarDataSet(entries, "BarDataSet");
        BarData data = new BarData(set);
        data.setBarWidth(0.9f);
        barChart.setData(data);
        barChart.setFitBars(true);
        barChart.invalidate();
        return view;
    }

    private ArrayList<GameModel> gameItems(){

        ArrayList<GameModel> list = new ArrayList<>();

        for(int i = 0; i < 3; i++) {

            GameModel gameModel = new GameModel();
            gameModel.setGameName(myImageNameList[i]);
            gameModel.setGameImg(myImageList[i]);
            list.add(gameModel);

        }
        return list;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FragmentHomeViewModel.class);
        // TODO: Use the ViewModel
    }

    static class MoodCount implements BaseColumns {
        static final String TABLE_NAME = "mood_count";
        static final String COLUMN_MOOD_1 = "mood1";
        static final String COLUMN_MOOD_2 = "mood2";
        static final String COLUMN_MOOD_3 = "mood3";
        static final String COLUMN_MOOD_4 = "mood4";
        static final String COLUMN_MOOD_5 = "mood5";
    }

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + MoodCount.TABLE_NAME + " (" +
            MoodCount._ID + " INTEGER PRIMARY KEY, " +
            MoodCount.COLUMN_MOOD_1 + " INT, " +
            MoodCount.COLUMN_MOOD_2 + " INT, " +
            MoodCount.COLUMN_MOOD_3 + " INT, " +
            MoodCount.COLUMN_MOOD_4 + " INT, " +
            MoodCount.COLUMN_MOOD_5 + " INT" +
            ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MoodCount.TABLE_NAME;

    public static class MoodDBEntryHelper extends SQLiteOpenHelper {
        static final int DATABASE_VERSION = 1;
        static final String DATABASE_NAME = "Mood.db";

        MoodDBEntryHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_menu_profile:
                getActivity().startActivity(new Intent(getContext(), ActivityYou.class));
                return true;
            default:
                return false;
        }
    }

    static void startAnxiety()
    {
        //context.startActivity(new Intent(context, Anxiety_Game.class));
    }
    static void startStress()
    {
        context.startActivity(new Intent(context, Stress_Game.class));
    }
    static void startDepress()
    {
        //context.startActivity(new Intent(context, Depression_Game.class));
    }
}

