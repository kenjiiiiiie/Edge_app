package app.helianthus.edge;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class FragmentJournal extends Fragment {
    static Context context;
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + JournalEntry.TABLE_NAME + " (" +
            JournalEntry._ID + " INTEGER PRIMARY KEY," +
            JournalEntry.COLUMN_DATE_TIME + " TEXT," +
            JournalEntry.COLUMN_ENTRY + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + JournalEntry.TABLE_NAME;

    private FragmentJournalViewModel mViewModel;
    static RecyclerView recyclerView;
    private boolean isChecked = true;
    SwipeRefreshLayout refreshLayout;

    private JournalDBEntryHelper dbHelper ;
    private SQLiteDatabase database;

    View view;
    private MaterialButton btnCreate;
    LinearLayout emptyState;

    private ArrayList<String>journalContent, journalDate, journalPreview;

    public static FragmentJournal newInstance() {
        return new FragmentJournal();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_journal, container, false);
        journalDate = new ArrayList<>();
        journalContent = new ArrayList<>();
        journalPreview = new ArrayList<>();

        dbHelper = new JournalDBEntryHelper(getContext());
        database = dbHelper.getReadableDatabase();
        context = getContext();
        Toolbar mTopToolbar = view.findViewById(R.id.journal_toolbar);
        mTopToolbar.inflateMenu(R.menu.journal_app_bar_menu);

        recyclerView = view.findViewById(R.id.journal_recycler_view);

        btnCreate = view.findViewById(R.id.journal_btn_create);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ActivityWriteJournal.class);
                startActivity(intent);
            }
        });

        emptyState = view.findViewById(R.id.journal_empty_state);

        refreshLayout = view.findViewById(R.id.journal_swipe_refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i("LOG_TAG", "Refreshing");
            }
        });

        //Read Database
        String[] projection = {JournalEntry._ID, JournalEntry.COLUMN_DATE_TIME, JournalEntry.COLUMN_ENTRY};
        String sortOrder = JournalEntry.COLUMN_DATE_TIME + " DESC";
        Cursor cursor = database.query(
                JournalEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        if(cursor.getCount() > 0)
        {
            recyclerView.setVisibility(View.VISIBLE);
            emptyState.setVisibility(View.GONE);
            while(cursor.moveToNext()){
                String date = cursor.getString(cursor.getColumnIndexOrThrow(JournalEntry.COLUMN_DATE_TIME));
                String content = cursor.getString(cursor.getColumnIndexOrThrow(JournalEntry.COLUMN_ENTRY));
                String preview = content.length() > 100 ? content.substring(0, 100) + "...": content;
                journalDate.add(date);
                journalContent.add(content);
                journalPreview.add(preview);
            }
        }
        else {
            emptyState.setVisibility(View.VISIBLE);
        }
        cursor.close();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new JournalRecycleAdapter(journalDate, journalContent, journalPreview));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FragmentJournalViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem gridCheck = menu.findItem(R.id.journal_menu_grid_toggle);
        gridCheck.setChecked(isChecked);
        //return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.journal_menu_grid_toggle:
                if(isChecked){
                    isChecked = !item.isChecked();
                    item.setChecked(isChecked);
                }
                else
                {
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(new JournalRecycleAdapter(journalDate, journalContent, journalPreview));
                    isChecked = !item.isChecked();
                    item.setChecked(isChecked);
                }
                Toast.makeText(getContext(), "Clicked grid view", Toast.LENGTH_SHORT).show();
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                recyclerView.setAdapter(new JournalRecycleAdapter(journalDate, journalContent, journalPreview));

                return true;
            default:
                return false;
        }
    }

    static class JournalEntry implements BaseColumns {
        static final String TABLE_NAME = "journal_entry";
        static final String COLUMN_DATE_TIME = "date_time_entry";
        static final String COLUMN_ENTRY = "entry";
    }

    public static class JournalDBEntryHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        static final int DATABASE_VERSION = 1;
        static final String DATABASE_NAME = "Journal.db";

        JournalDBEntryHelper(Context context) {
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

    static void startWriteJournal_Activity()
    {
        context.startActivity(new Intent(context, ActivityWriteJournal.class));
    }
}