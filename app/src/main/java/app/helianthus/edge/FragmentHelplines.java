package app.helianthus.edge;

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

public class FragmentHelplines extends Fragment implements View.OnClickListener{
    private MaterialButton btn_call, btn_message;
    private ConstraintLayout btn_link1, btn_link2;

    private FragmentHelplinesViewModel mViewModel;

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

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FragmentHelplinesViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {
        Intent newActivity;
        switch (v.getId())
        {
            case R.id.helplines_btn_call:
                newActivity = new Intent(Intent.ACTION_CALL);
                newActivity.setData(Uri.parse("tel:09175584673"));
                startActivity(newActivity);
                break;
            case R.id.helplines_btn_message:
                newActivity = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:09175584673"));
                startActivity(newActivity);
                break;
            case R.id.helplines_btn_link1:
                newActivity= new Intent(Intent.ACTION_VIEW);
                newActivity.setData(Uri.parse("https://www.pap.org.ph/"));
                startActivity(newActivity);
                break;
            case R.id.helplines_btn_link2:
                newActivity= new Intent(Intent.ACTION_VIEW);
                newActivity.setData(Uri.parse("https://ppa.philpsych.ph/"));
                startActivity(newActivity);
                break;
        }
    }
}
