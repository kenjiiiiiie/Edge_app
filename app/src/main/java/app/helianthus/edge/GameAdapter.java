package app.helianthus.edge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<GameModel> imageModelArrayList;

    public GameAdapter(Context ctx, ArrayList<GameModel> imageModelArrayList) {

        inflater = LayoutInflater.from(ctx);
        this.imageModelArrayList = imageModelArrayList;

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

        holder.iv.setImageResource(imageModelArrayList.get(position).getGameImg());
        holder.tv.setText(imageModelArrayList.get(position).getGameName());

    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView iv;

        MyViewHolder(View itemView) {

            super(itemView);

            tv = itemView.findViewById(R.id.home_mini_games_title);
            iv = itemView.findViewById(R.id.home_mini_games_img);

        }

    }

}
