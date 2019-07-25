package com.tcsoft.searchmachinary.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.tcsoft.searchmachinary.R;
import com.tcsoft.searchmachinary.bean.Book;

import java.util.List;


public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<Book> list;

    private boolean isRanking;

    private OnClickListener itemOnClickListener;


    public SearchAdapter(List<Book> list, Context context) {
        this.context = context;
        this.list = list;
    }

    public void setRanking() {
        isRanking = true;
    }


    public void setOnClickListener(OnClickListener onClickListener) {
        this.itemOnClickListener = onClickListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_search_book, parent, false);
        view.setOnClickListener(this);
        return new SearchViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SearchViewHolder searchViewHolder = (SearchViewHolder) holder;
        if (isRanking) {
            searchViewHolder.llRanking.setVisibility(View.VISIBLE);
            switch (position) {
                case 0:
                    searchViewHolder.rlRanking.setBackgroundResource(R.drawable.bg_no_1);
                    break;
                case 1:
                    searchViewHolder.rlRanking.setBackgroundResource(R.drawable.bg_no_2);
                    break;
                case 2:
                    searchViewHolder.rlRanking.setBackgroundResource(R.drawable.bg_no_3);
                    break;
                default:
                    searchViewHolder.rlRanking.setBackgroundResource(R.drawable.shape_ranking);
                    break;
            }
            int ranking = position + 1;
            searchViewHolder.tvRanking.setText(String.valueOf(ranking));
            searchViewHolder.tvReadingCount.setText(String.format(context.getResources().getString(R.string.total_reading_count), list.get(position).getLoanNum()));
        }
        searchViewHolder.tvBookTitle.setText(list.get(position).getTitle());
        searchViewHolder.tvAuthor.setText(list.get(position).getAuthor());
        searchViewHolder.tvPublisher.setText(list.get(position).getPublisher());
        searchViewHolder.tvCallno.setText(list.get(position).getCallNo());
        Glide.with(context).load(list.get(position).getCover()).placeholder(R.drawable.icon_nocover).error(R.drawable.icon_nocover).into(searchViewHolder.ivCover);
        holder.itemView.setTag(position);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    private class SearchViewHolder extends RecyclerView.ViewHolder {
        private TextView tvBookTitle, tvAuthor, tvPublisher, tvCallno;
        private TextView tvRanking, tvReadingCount;
        private ImageView ivCover;
        private LinearLayout llRanking;
        private RelativeLayout rlRanking;

        private SearchViewHolder(View view) {
            super(view);
            llRanking = view.findViewById(R.id.ll_ranking_item_book_search);
            rlRanking = view.findViewById(R.id.rl_ranking_item_book_search);
            tvRanking = view.findViewById(R.id.tv_ranking_item_book_search);
            tvReadingCount = view.findViewById(R.id.tv_count_item_book_search);

            tvBookTitle = view.findViewById(R.id.tv_title_item_search_book);
            tvAuthor = view.findViewById(R.id.tv_author_item_search_book);
            tvPublisher = view.findViewById(R.id.tv_publisher_item_search_book);
            tvCallno = view.findViewById(R.id.tv_callno_item_search_book);
            ivCover = view.findViewById(R.id.iv_cover_item_search_book);

        }
    }


    public interface OnClickListener {
        void onItemClick(View v, int position);
    }


    @Override
    public void onClick(View v) {
        if (itemOnClickListener != null && v.getTag() != null)
            itemOnClickListener.onItemClick(v, (Integer) v.getTag());
    }


}
