package app.helianthus.edge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReadRecycleAdapter extends RecyclerView.Adapter<ReadRecycleAdapter.ViewHolder> {

    private String title[], content[], cite[];

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout cardView;
        TextView readTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.read_card);
            readTextView = itemView.findViewById(R.id.read_card_content);
        }
    }

    ReadRecycleAdapter(String Title[], String Content[], String Cite[]) {
        this.title =Title;
        this.content = Content;
        this.cite= Cite;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());

        View materialCard = inflater.inflate(R.layout.read_card, parent, false);
        return new ViewHolder(materialCard);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Objects.requireNonNull(holder).readTextView.setText(title[position]);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityReadList.startWriteJournal_Activity();
            }
        });
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

}
