package app.helianthus.edge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityReadsContent extends AppCompatActivity {
    TextView reads_title, reads_body, reads_cite;
    Button btnNext, btnPrevious, btnNextChapter;
    static String title_arr[], body_arr[], cite_arr[];
    static int positon_arr = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reads_content);
        reads_title = findViewById(R.id.reads_title);
        reads_body = findViewById(R.id.reads_content);
        reads_cite = findViewById(R.id.reads_cite);

        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positon_arr++;
                reads_title.setText(title_arr[positon_arr]);
                reads_body.setText(body_arr[positon_arr]);
                reads_cite.setText(cite_arr[positon_arr]);

                if(positon_arr == body_arr.length-1)
                {
                    btnNext.setVisibility(View.INVISIBLE);
                    btnPrevious.setVisibility(View.INVISIBLE);
                    btnNextChapter.setText("PREVIOUS CHAPTER");
                    btnNextChapter.setCompoundDrawablesRelative(getResources().getDrawable(R.drawable.ic_skip_previous_24), null , null, null);
                    btnNextChapter.setVisibility(View.VISIBLE);
                }
            }

        });

        btnPrevious = findViewById(R.id.btnPrevious);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positon_arr--;
                reads_title.setText(title_arr[positon_arr]);
                reads_body.setText(body_arr[positon_arr]);
                reads_cite.setText(cite_arr[positon_arr]);
                if(positon_arr == 0 )
                {
                    btnNext.setVisibility(View.INVISIBLE);
                    btnPrevious.setVisibility(View.INVISIBLE);
                    btnNextChapter.setText("NEXT CHAPTER");
                    btnNextChapter.setCompoundDrawablesRelative(null, null , null,
                            getResources().getDrawable(R.drawable.ic_skip_next_24));
                    btnNextChapter.setVisibility(View.VISIBLE);
                }
            }
        });

        btnNextChapter = findViewById(R.id.btnNextChapter);
        btnNextChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(positon_arr == 0) {
                    positon_arr++;
                    reads_title.setText(title_arr[positon_arr]);
                    reads_body.setText(body_arr[positon_arr]);
                    reads_cite.setText(cite_arr[positon_arr]);
                    btnNext.setVisibility(View.VISIBLE);
                    btnPrevious.setVisibility(View.VISIBLE);
                    btnNextChapter.setText("PREVIOUS CHAPTER");
                    btnNextChapter.setVisibility(View.INVISIBLE);
                }else if (positon_arr == body_arr.length-1)
                {
                    positon_arr--;
                    reads_title.setText(title_arr[positon_arr]);
                    reads_body.setText(body_arr[positon_arr]);
                    reads_cite.setText(cite_arr[positon_arr]);
                    btnNext.setVisibility(View.VISIBLE);
                    btnPrevious.setVisibility(View.VISIBLE);
                    btnNextChapter.setText("NEXT CHAPTER");
                    btnNextChapter.setVisibility(View.INVISIBLE);
                }
            }
        });

        reads_title.setText(title_arr[positon_arr]);
        reads_body.setText(body_arr[positon_arr]);
        reads_cite.setText(cite_arr[positon_arr]);



    }
}
