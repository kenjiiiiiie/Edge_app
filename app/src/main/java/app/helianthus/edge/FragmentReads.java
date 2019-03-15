package app.helianthus.edge;

import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.lifecycle.ViewModelProviders;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.card.MaterialCardView;

public class FragmentReads extends Fragment {

    private FragmentReadsViewModel mViewModel;
    private LinearLayout cardStress, cardAnxiety, cardDepression;
    private Intent intent;

    public static FragmentReads newInstance() {
        return new FragmentReads();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reads, container, false);
        cardStress = view.findViewById(R.id.reads_card_stress);
        cardAnxiety = view.findViewById(R.id.reads_card_anxiety);
        cardDepression = view.findViewById(R.id.reads_card_depression);

        cardStress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), ActivityStress.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FragmentReadsViewModel.class);
        // TODO: Use the ViewModel
    }
}
