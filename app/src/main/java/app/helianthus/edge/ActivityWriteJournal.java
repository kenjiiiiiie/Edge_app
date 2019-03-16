package app.helianthus.edge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
            write_title.setText("Edit Journal");
            write_editText.setText(write_content);
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

                return true;
            default:
                return false;
        }
    }
}
