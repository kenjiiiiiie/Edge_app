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

public class JournalRecycleAdapter extends RecyclerView.Adapter<JournalRecycleAdapter.ViewHolder> {
    private ArrayList<String> dateText, contentText, preview;

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout cardView;
        TextView cardTextView, dateTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.journal_card);
            cardTextView = itemView.findViewById(R.id.journal_card_content);
            dateTextView =  itemView.findViewById(R.id.journal_card_date);
        }
    }

    JournalRecycleAdapter(ArrayList<String> dateTextView, ArrayList<String> contentTextView, ArrayList<String> preview) {
        this.dateText = dateTextView;
        this.contentText = contentTextView;
        this.preview = preview;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());

        View materialCard = inflater.inflate(R.layout.journal_card, parent, false);
        return new ViewHolder(materialCard);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Objects.requireNonNull(holder).cardTextView.setText(preview.get(position));
        Objects.requireNonNull(holder).dateTextView.setText(dateText.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityWriteJournal.write_isEdit = true;
                ActivityWriteJournal.write_content = contentText.get(position);
                FragmentJournal.startWriteJournal_Activity();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dateText.size();
    }

}
