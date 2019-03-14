package app.helianthus.edge;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;

import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class FragmentJournal extends Fragment {
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

    View view;

    private boolean isChecked = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_journal, container, false);

        Toolbar mTopToolbar = view.findViewById(R.id.journal_toolbar);
        mTopToolbar.inflateMenu(R.menu.journal_app_bar_menu);

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
                isChecked = !item.isChecked();
                item.setChecked(isChecked);
                return true;
            default:
                return false;
        }
    }

    public static class JournalEntry implements BaseColumns {
        static final String TABLE_NAME = "journal_entry";
        static final String COLUMN_DATE_TIME = "date_time_entry";
        static final String COLUMN_ENTRY = "entry";
    }

    public class JournalDBEntryHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "FeedReader.db";

        public JournalDBEntryHelper(Context context) {
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
}