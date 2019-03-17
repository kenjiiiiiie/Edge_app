package app.helianthus.edge;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ActivityWriteJournal extends AppCompatActivity {
    TextView write_title;
    EditText write_editText;
    static boolean write_isEdit = false;
    static String write_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_journal);

        write_title = findViewById(R.id.journal_write_title);
        write_editText = findViewById(R.id.journal_write_edittext);

        View view = findViewById(R.id.journal_write_root);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }

        Toolbar toolbar = findViewById(R.id.journal_write_toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.journal_write_app_bar_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_cancel);

        if(write_isEdit)
        {
            write_title.setText("");
            write_editText.setText(write_content);
        }
        else
        {
            write_title.setText(getString(R.string.journal_write_title));
            write_editText.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.journal_write_app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem btnSave = menu.findItem(R.id.journal_write_save);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.journal_write_save:
                //Save journal
                String date_n = new SimpleDateFormat("MMMM dd, yyyy hh:mm aa", Locale.getDefault()).format(new Date());

                FragmentJournal.JournalDBEntryHelper dbHelper = new FragmentJournal.JournalDBEntryHelper(this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(FragmentJournal.JournalEntry.COLUMN_ENTRY, write_editText.getText().toString());
                values.put(FragmentJournal.JournalEntry.COLUMN_DATE_TIME, date_n);
                long newRowId = db.insert(FragmentJournal.JournalEntry.TABLE_NAME, null, values);
                Toast.makeText(this, "Journal Saved", Toast.LENGTH_SHORT).show();
                FragmentJournal.recyclerView.invalidate();
                onBackPressed();
                return true;
            case android.R.id.home:
                write_isEdit = false;
                return false;
            default:
                return false;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        write_isEdit = false;
    }
}
