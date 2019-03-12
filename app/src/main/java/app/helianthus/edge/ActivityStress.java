package app.helianthus.edge;

import android.os.Bundle;
import android.app.Activity;

import java.util.Objects;

public class ActivityStress extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stress);
        //Objects.requireNonNull(getActionBar()).setDisplayHomeAsUpEnabled(true);
    }
}
