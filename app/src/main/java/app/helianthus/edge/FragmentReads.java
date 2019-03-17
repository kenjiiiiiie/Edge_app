package app.helianthus.edge;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
                intent = new Intent(getContext(), ActivityReadList.class);
                ActivityReadList.EXTRA_CHOSEN_READS = "Stress";
                startActivity(intent);
            }
        });

        cardAnxiety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), ActivityReadList.class);
                ActivityReadList.EXTRA_CHOSEN_READS = "Anxiety";
                startActivity(intent);
            }
        });

        cardDepression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), ActivityReadList.class);
                ActivityReadList.EXTRA_CHOSEN_READS = "Depression";
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
