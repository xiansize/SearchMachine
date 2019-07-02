package com.tcsoft.searchmachinary.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tcsoft.searchmachinary.R;
import com.tcsoft.searchmachinary.bean.Book;

import java.util.List;


public class SearchBookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<Book> list;
    private Context context;
    private ItemOnClickListener itemOnClickListener;


    private boolean isRanking;


    //view
    private static final int TYPE_BOOK = 1;
    private static final int TYPE_FOOTER = 0;

    private int status_loading = 1;

    //loadin
    private static final int END = 0;
    private static final int LOADING = 1;


    public SearchBookAdapter(List<Book> list, Context context) {
        this.list = list;
        this.context = context;
    }


    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }


    public void setStatus_loading(int status_loading) {
        this.status_loading = status_loading;
        notifyDataSetChanged();
    }


    public void setRanking(boolean ranking) {
        isRanking = ranking;
    }

    @Override
    public int getItemViewType(int position) {
        if (++position == getItemCount())
            return TYPE_FOOTER;
        else
            return TYPE_BOOK;
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

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_BOOK) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_item_search_book, parent, false);
            view.setOnClickListener(this);
            return new SearchViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_item_footer_search_book, parent, false);
            return new FooterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SearchViewHolder) {
            SearchViewHolder searchViewHolder = ((SearchViewHolder) holder);
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
            Picasso.with(context).load(list.get(position).getCover()).placeholder(R.drawable.icon_nocover).error(R.drawable.icon_nocover).into(searchViewHolder.ivCover);
            holder.itemView.setTag(position);
        } else {
            switch (status_loading) {
                case LOADING:
                    ((FooterViewHolder) holder).tvFooter.setText(context.getResources().getString(R.string.search_loading));
                    break;
                case END:
                    ((FooterViewHolder) holder).tvFooter.setText(context.getResources().getString(R.string.search_end));
                    break;
            }

        }

    }


    @Override
    public int getItemCount() {
        return list.size() + 1;
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

    class FooterViewHolder extends RecyclerView.ViewHolder {
        private TextView tvFooter;

        FooterViewHolder(View view) {
            super(view);
            tvFooter = view.findViewById(R.id.tv_tips_footer_search_book);
        }
    }


    @Override
    public void onClick(View v) {
        if (itemOnClickListener != null && v.getTag() != null)
            itemOnClickListener.onItemClick(v, (Integer) v.getTag());
    }


    public interface ItemOnClickListener {
        void onItemClick(View view, int position);
    }


}
