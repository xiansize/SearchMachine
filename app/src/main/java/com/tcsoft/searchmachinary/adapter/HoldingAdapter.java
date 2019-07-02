package com.tcsoft.searchmachinary.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tcsoft.searchmachinary.R;
import com.tcsoft.searchmachinary.bean.Holding;

import java.util.List;


public class HoldingAdapter extends RecyclerView.Adapter<HoldingAdapter.HoldingViewHolder> {

    private List<Holding> list;
    private Context context;

    public HoldingAdapter(List<Holding> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public HoldingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_holding_book_details, parent, false);
        return new HoldingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoldingViewHolder holder, int position) {
        holder.tvBarcode.setText(list.get(position).getBarcode());
        holder.tvLocation.setText(list.get(position).getLocal() + list.get(position).getShelf());
        holder.tvStatus.setText(list.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class HoldingViewHolder extends RecyclerView.ViewHolder {

        private TextView tvBarcode, tvLocation, tvStatus;

        HoldingViewHolder(View view) {
            super(view);
            tvBarcode = view.findViewById(R.id.tv_barcode_item_book_holding);
            tvLocation = view.findViewById(R.id.tv_location_item_book_holding);
            tvStatus = view.findViewById(R.id.tv_status_item_book_holding);

        }
    }


}
