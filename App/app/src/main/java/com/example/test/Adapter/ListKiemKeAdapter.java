package com.example.test.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.InventoryDetail;
import com.example.test.Model.Inventory;
import com.example.test.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ListKiemKeAdapter extends RecyclerView.Adapter<ListKiemKeAdapter.ViewHolder> implements Filterable {

    private List<Inventory> inventories;

    private List<Inventory> listSearch;
    public ListKiemKeAdapter(List<Inventory> _inventories){
        this.inventories=_inventories;
        this.listSearch=_inventories;
    }
    @Override
    public int getItemCount(){
        return inventories.size();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ho_so,parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Inventory inventory=inventories.get(position);
        Context context=holder.itemView.getContext();
        String id=inventories.get(position).getId();

        holder.Id.setText(inventory.getId());

        if(inventory.getDate()==null)
        {
            holder.date.setText("");
        }
        else
        {
            String date=new SimpleDateFormat("dd-MM-yyyy").format((inventory.getDate()));
            holder.date.setText(date);
        }

        holder.invenBase.setText("Cơ sở: " + inventory.getInvenBase());
        holder.invenUnit.setText("Đơn vị: " +inventory.getInvenUnit());
        if (inventory.getFinish() != null){
            if (inventory.getFinish() == true){
                holder.Id.setBackgroundColor(Color.parseColor("#30cc00"));
            }
            else {
                holder.Id.setBackgroundColor(Color.RED);
            }


        }
        else {
            holder.Id.setBackgroundColor(Color.RED);

        }
        holder.Id.setTextColor(Color.WHITE);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), InventoryDetail.class);
                intent.putExtra("invenId",inventory.getId());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String search=charSequence.toString();
                if(search.isEmpty()){
                    inventories=listSearch;
                }
                else{
                    List<Inventory> list=new ArrayList<>();
                    for(Inventory inventory:listSearch){
                        if(inventory.getId().toLowerCase().contains(search.toLowerCase())){
                            list.add(inventory);
                        }
                    }
                    inventories=list;
                }
                FilterResults results=new FilterResults();
                results.values=(inventories);
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                inventories=(List<Inventory>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView Id;
        public TextView date;
        public TextView invenUser;
        public TextView invenBase;
        public TextView invenUnit;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            Id=(TextView) itemView.findViewById(R.id.assetId);
            date=(TextView) itemView.findViewById(R.id.assetStatus);
            invenBase=(TextView) itemView.findViewById(R.id.baseId);
            invenUnit=(TextView) itemView.findViewById(R.id.unitId);
        }
    }
}
