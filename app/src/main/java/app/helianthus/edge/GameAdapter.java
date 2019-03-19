package app.helianthus.edge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<GameModel> imageModelArrayList;
    private String imageName[];

    public GameAdapter(Context ctx, ArrayList<GameModel> imageModelArrayList,
                       String imageName[]) {

        inflater = LayoutInflater.from(ctx);
        this.imageModelArrayList = imageModelArrayList;
        this.imageName =imageName;

    }

    @NonNull
    @Override
    public GameAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = inflater.inflate(R.layout.item_recycler_minigame, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull GameAdapter.MyViewHolder holder, int position) {
        holder.tv.setText(imageName[position]);
        holder.iv.setImageResource(imageModelArrayList.get(position).getGameImg());

        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position)
                {
                    case 0:
                        FragmentHome.startStress();
                        break;
                    case 1:
                            FragmentHome.startAnxiety();
                        break;
                    case 2:
                        FragmentHome.startDepress();
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView iv;
        MaterialCardView cardView;
        View forClick;

        MyViewHolder(View itemView) {

            super(itemView);
            cardView = itemView.findViewById(R.id.home_card_mini_games);
            tv = itemView.findViewById(R.id.home_mini_games_title);
            iv = itemView.findViewById(R.id.home_mini_games_img);
            forClick = itemView.findViewById(R.id.forClick);
        }

    }

}
