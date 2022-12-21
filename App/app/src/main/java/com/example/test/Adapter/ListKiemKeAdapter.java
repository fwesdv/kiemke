package com.example.test.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.InventoryDetail;
import com.example.test.Model.Inventory;
import com.example.test.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class ListKiemKeAdapter extends RecyclerView.Adapter<ListKiemKeAdapter.ViewHolder> {

    private List<Inventory> inventories;

    public ListKiemKeAdapter(List<Inventory> _inventories){
        this.inventories=_inventories;
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

        holder.invenBase.setText(inventory.getInvenBase());
        holder.invenUnit.setText(inventory.getInvenUnit());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), InventoryDetail.class);
                intent.putExtra("invenId",inventory.getId());
                view.getContext().startActivity(intent);
            }
        });

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
