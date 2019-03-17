package app.helianthus.edge;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class FragmentJournal extends Fragment {
    private ViewGroup parent;
    static Context context;
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + JournalEntry.TABLE_NAME + " (" +
            JournalEntry._ID + " INTEGER PRIMARY KEY," +
            JournalEntry.COLUMN_DATE_TIME + " TEXT," +
            JournalEntry.COLUMN_ENTRY + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + JournalEntry.TABLE_NAME;

    private FragmentJournalViewModel mViewModel;

    public static FragmentJournal newInstance() {
        return new FragmentJournal();
    }
    static RecyclerView recyclerView;
    private boolean [] isChecked = {true};

    private JournalDBEntryHelper dbHelper ;
    private SQLiteDatabase database;

    View view;
    private MaterialButton btnCreate;

    private ArrayList<String>journalContent, journalDate, journalPreview;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_journal, container, false);
        parent = view.findViewById(R.id.journal_empty_state);
        journalDate = new ArrayList<>();
        journalContent = new ArrayList<>();
        journalPreview = new ArrayList<>();

        dbHelper = new JournalDBEntryHelper(getContext());
        database = dbHelper.getReadableDatabase();
        context = getContext();
        final Toolbar mTopToolbar = view.findViewById(R.id.journal_toolbar);
        mTopToolbar.inflateMenu(R.menu.journal_app_bar_menu);
        mTopToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.journal_menu_grid_toggle:
                        if(isChecked[0]){
                            item.setIcon(R.drawable.ic_default_view);
                            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                            recyclerView.setAdapter(new JournalRecycleAdapter(journalDate, journalContent, journalPreview));
                            isChecked[0] = false;
                            item.setChecked(isChecked[0]);

                        }
                        else
                        {
                            item.setIcon(R.drawable.ic_grid_view);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setAdapter(new JournalRecycleAdapter(journalDate, journalContent, journalPreview));
                            isChecked[0] = true;
                            item.setChecked(isChecked[0]);
                        }
                }
                return false;
            }
        });
        recyclerView = view.findViewById(R.id.journal_recycler_view);
        btnCreate = view.findViewById(R.id.journal_btn_create);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ActivityWriteJournal.class);
                startActivity(intent);
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
            parent.removeAllViews();
            recyclerView.setVisibility(View.VISIBLE);
            while(cursor.moveToNext()){
                String date = cursor.getString(cursor.getColumnIndexOrThrow(JournalEntry.COLUMN_DATE_TIME));
                String content = cursor.getString(cursor.getColumnIndexOrThrow(JournalEntry.COLUMN_ENTRY));
                String preview = content.length() > 100 ? content.substring(0, 100) + "...": content;
                journalDate.add(date);
                journalContent.add(content);
                journalPreview.add(preview);
            }
        }
        cursor.close();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new JournalRecycleAdapter(journalDate, journalContent, journalPreview));
        return view;
    }

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FragmentJournalViewModel.class);
        // TODO: Use the ViewModel
    }**/

    //@Override
    //public void onPrepareOptionsMenu(Menu menu) {
    //    MenuItem gridCheck = menu.findItem(R.id.journal_menu_grid_toggle);
    //    gridCheck.setChecked(isChecked[0]);
        //return true;
    //}


    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.journal_menu_grid_toggle:
                Intent intent = new Intent(getContext(), ActivityWriteJournal.class);
                startActivity(intent);
                if(isChecked[0]){
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    recyclerView.setAdapter(new JournalRecycleAdapter(journalDate, journalContent, journalPreview));
                    isChecked[0] = false;
                    item.setChecked(isChecked[0]);

                }
                else
                {
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(new JournalRecycleAdapter(journalDate, journalContent, journalPreview));
                    isChecked[0] = true;
                    item.setChecked(isChecked[0]);
                }
                return true;
            default:
                return false;
        }
    }**/
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