package com.tcsoft.searchmachinary.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tcsoft.searchmachinary.R;
import com.tcsoft.searchmachinary.bean.Consult;

import java.util.List;


public class ConsultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Consult> list;
    private Context context;
    private static final int IS_CLIENT = 1;
    private static final int NOT_CLIENT = 0;


    public ConsultAdapter(List<Consult> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == IS_CLIENT) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_item_advise_client, parent, false);
            return new ViewHolderClient(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_item_advise_service, parent, false);
            return new ViewHolderService(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderClient) {
            ((ViewHolderClient) holder).tvClientText.setText(list.get(position).getText());
            ((ViewHolderClient) holder).tvClientTime.setText(list.get(position).getTime());
        } else {
            ((ViewHolderService) holder).tvServiceText.setText(list.get(position).getText());
            ((ViewHolderService) holder).tvServiceTime.setText(list.get(position).getTime());
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (list.get(position).isClient()) {
            return IS_CLIENT;
        } else {
            return NOT_CLIENT;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    private class ViewHolderService extends RecyclerView.ViewHolder {
        private TextView tvServiceText;
        private TextView tvServiceTime;


        private ViewHolderService(View view) {
            super(view);
            tvServiceText = view.findViewById(R.id.tv_context_service);
            tvServiceTime = view.findViewById(R.id.tv_time_service);

        }
    }


    class ViewHolderClient extends RecyclerView.ViewHolder {
        private TextView tvClientText;
        private TextView tvClientTime;

        ViewHolderClient(View view) {
            super(view);
            tvClientText = view.findViewById(R.id.tv_context_client);
            tvClientTime = view.findViewById(R.id.tv_time_client);
        }
    }
}
