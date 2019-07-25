package com.tcsoft.searchmachinary.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tcsoft.searchmachinary.R;


public class LoadWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private RecyclerView.Adapter adapter;

    //view
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 0;

    private int statusLoading = 1;

    //loading
    private static final int END = 0;
    private static final int LOADING = 1;


    public LoadWrapper(RecyclerView.Adapter adapter, Context context) {
        this.adapter = adapter;
        this.context = context;
    }


    public void setStatusLoading() {
        this.statusLoading = 0;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_footer_search_book, parent, false);
            return new FooterViewHolder(view);
        } else {
            return adapter.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            switch (statusLoading) {
                case LOADING:
                    footerViewHolder.tvFooter.setText(context.getResources().getString(R.string.search_loading));
                    break;
                case END:
                    footerViewHolder.tvFooter.setText(context.getResources().getString(R.string.search_end));
                    break;
            }
        } else {
            adapter.onBindViewHolder(holder, position);
        }
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    // 如果当前是footer的位置，那么该item占据2个单元格，正常情况下占据1个单元格
                    return getItemViewType(position) == TYPE_FOOTER ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return adapter.getItemCount() + 1;
    }


    class FooterViewHolder extends RecyclerView.ViewHolder {
        private TextView tvFooter;

        FooterViewHolder(View view) {
            super(view);
            tvFooter = view.findViewById(R.id.tv_tips_footer_search_book);
        }
    }


}
