package com.tcsoft.searchmachinary.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tcsoft.searchmachinary.R;
import com.tcsoft.searchmachinary.bean.Consult;

import java.util.List;

/**
 * Created by Admin on 2019/5/17.
 */

public class ConsultAdapter extends RecyclerView.Adapter<ConsultAdapter.ViewHolder> {


    private List<Consult> list;
    private LayoutInflater layoutInflater;

    public ConsultAdapter(List<Consult> list, Context context) {
        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_item_advise, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list.get(position).isClient()) {
            holder.rlService.setVisibility(View.GONE);
            holder.tvClientText.setText(list.get(position).getText());
            holder.tvClientTime.setText(list.get(position).getTime());
        } else {
            holder.rlClient.setVisibility(View.GONE);
            holder.tvServiceText.setText(list.get(position).getText());
            holder.tvServiceTime.setText(list.get(position).getTime());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvServiceText;
        private TextView tvClientText;
        private RelativeLayout rlService;
        private RelativeLayout rlClient;
        private TextView tvServiceTime;
        private TextView tvClientTime;

        ViewHolder(View view) {
            super(view);
            tvServiceText = view.findViewById(R.id.tv_context_service);
            tvClientText = view.findViewById(R.id.tv_context_client);
            rlService = view.findViewById(R.id.rl_container_service);
            rlClient = view.findViewById(R.id.rl_container_client);
            tvServiceTime = view.findViewById(R.id.tv_time_service);
            tvClientTime = view.findViewById(R.id.tv_time_client);
        }
    }
}
