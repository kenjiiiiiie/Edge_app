package app.helianthus.edge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private ListData[] listData;

    public ListAdapter(ListData[] listData) {
        this.listData = listData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_recycler_list, parent,false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ListData myListData = listData[position];
        holder.itemTitle.setText(listData[position].getListTitle());
        holder.itemNumber.setText(listData[position].getListNumber());
        holder.itemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: " + myListData.getListTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemNumber, itemTitle;
        public FrameLayout itemList;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemNumber = itemView.findViewById(R.id.list_number);
            this.itemTitle = itemView.findViewById(R.id.list_title);
            itemList = itemView.findViewById(R.id.list_item_root);
        }
    }

}
