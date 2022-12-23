package com.example.test.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Model.Asset;
import com.example.test.Model.Inventory;
import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

public class ListAssetAdapter extends RecyclerView.Adapter<ListAssetAdapter.ViewHolder> implements Filterable {
    private List<Asset> assetList;
    private List<Asset> assetListSearch;
    public ListAssetAdapter(List<Asset> _assetList){
        this.assetList=_assetList;
        this.assetListSearch=_assetList;
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String search=charSequence.toString();
                if(search.isEmpty()){
                    assetList=assetListSearch;
                }
                else{
                    List<Asset> list=new ArrayList<>();
                    for(Asset asset:assetListSearch){
                        if(asset.getAssetId().toLowerCase().contains(search.toLowerCase())){
                            list.add(asset);
                        }
                    }
                    assetList=list;
                }
                FilterResults results=new FilterResults();
                results.values=(assetList);
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                assetList=(List<Asset>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
