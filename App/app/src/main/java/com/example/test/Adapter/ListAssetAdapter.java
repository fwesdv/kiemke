package com.example.test.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Model.Asset;
import com.example.test.R;

import java.util.List;

public class ListAssetAdapter extends RecyclerView.Adapter<ListAssetAdapter.ViewHolder> {
    private List<Asset> assetList;
    public ListAssetAdapter(List<Asset> _assetList){
        this.assetList=_assetList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tai_san,parent, false);
        return new ListAssetAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Asset asset=assetList.get(position);
        Context context=holder.itemView.getContext();
        String id=assetList.get(position).getAssetId();
        holder.assetId.setText(asset.getAssetId());
        holder.status.setText(asset.getStatus());
        holder.description.setText(asset.getDescription());

    }

    @Override
    public int getItemCount() {
        return assetList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView assetId;
        public TextView status;
        public TextView description;
        public View layout;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            assetId=(TextView) itemView.findViewById(R.id.assetId);
            status=(TextView) itemView.findViewById(R.id.assetStatus);
            description=(TextView) itemView.findViewById(R.id.description);
            layout=(View) itemView.findViewById(R.id.linearLayout2);
        }
    }
}
