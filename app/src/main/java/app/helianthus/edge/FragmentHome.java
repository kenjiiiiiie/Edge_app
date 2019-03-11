package app.helianthus.edge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentHome extends Fragment {

    private FragmentHomeViewModel mViewModel;
    private RecyclerView recyclerView;
    private ArrayList<GameModel> imageModelArrayList;
    private GameAdapter adapter;

    BarChart barChart;

    private int[] myImageList = new int[]{R.drawable.img_game_stress, R.drawable.img_game_anxiety
            , R.drawable.img_game_depression};
    private String[] myImageNameList = new String[]{"Decide", "Protect", "Avoid"};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        androidx.appcompat.widget.Toolbar mTopToolbar = view.findViewById(R.id.home_toolbar);
        mTopToolbar.inflateMenu(R.menu.home_app_bar_menu);

        String date_n = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(new Date());
        TextView date = view.findViewById(R.id.home_date);
        date.setText(date_n);

        recyclerView  = view.findViewById(R.id.home_mini_games_recyclerview);
        imageModelArrayList = gameItems();
        adapter = new GameAdapter(getContext(), imageModelArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        //barChart = (BarChart) view.findViewById(R.id.mood_bar_chart);

        return view;
    }

    private ArrayList<GameModel> gameItems(){

        ArrayList<GameModel> list = new ArrayList<>();

        for(int i = 0; i < 3; i++) {

            GameModel gameModel = new GameModel();
            gameModel.setGameName(myImageNameList[i]);
            gameModel.setGameImg(myImageList[i]);
            list.add(gameModel);

        }

        return list;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FragmentHomeViewModel.class);
        // TODO: Use the ViewModel
    }

}
