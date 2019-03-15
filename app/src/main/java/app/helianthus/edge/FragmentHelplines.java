package app.helianthus.edge;

import androidx.appcompat.widget.TooltipCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

public class FragmentHelplines extends Fragment {

    private FragmentHelplinesViewModel mViewModel;
    private MaterialButton btn_call, btn_message;
    private ConstraintLayout btn_link1, btn_link2;
    private String url1 = "https://www.pmha.org.ph/";
    private String url2 = "http://ppa.philpsych.ph/";

    public static FragmentHelplines newInstance() {
        return new FragmentHelplines();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_helplines, container, false);

        btn_call = view.findViewById(R.id.helplines_btn_call);
        btn_message = view.findViewById(R.id.helplines_btn_message);
        btn_link1 = view.findViewById(R.id.helplines_btn_link1);
        btn_link2 = view.findViewById(R.id.helplines_btn_link2);

        TooltipCompat.setTooltipText(btn_link1, url1);
        TooltipCompat.setTooltipText(btn_link2, url2);

        btn_message.setOnClickListener(new MaterialButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_SENDTO).setData(Uri.parse("smsto:09175584673")));
            }
        });

        btn_call.setOnClickListener(new MaterialButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:09175584673")));
            }
        });

        btn_link1.setOnClickListener(new MaterialButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url1)));
            }
        });

        btn_link2.setOnClickListener(new MaterialButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url2)));
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FragmentHelplinesViewModel.class);
        // TODO: Use the ViewModel
    }
}
