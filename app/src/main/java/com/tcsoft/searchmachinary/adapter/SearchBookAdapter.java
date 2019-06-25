package com.tcsoft.searchmachinary.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tcsoft.searchmachinary.R;
import com.tcsoft.searchmachinary.bean.Book;

import java.util.List;


public class SearchBookAdapter extends RecyclerView.Adapter<SearchBookAdapter.ViewHolder> implements View.OnClickListener {

    private List<Book> list;
    private Context context;
    private ItemOnClickListener itemOnClickListener;

    public SearchBookAdapter(List<Book> list, Context context) {
        this.list = list;
        this.context = context;
    }


    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_search_book, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvBookTitle.setText(list.get(position).getTitle());
        holder.tvAuthor.setText(list.get(position).getAuthor());
        holder.tvPublisher.setText(list.get(position).getPublisher());
        holder.tvCallno.setText(list.get(position).getCallNo());

        Picasso.with(context)
                .load(list.get(position).getCover())
                .placeholder(R.drawable.icon_nocover)
                .error(R.drawable.icon_nocover)
                .into(holder.ivCover);

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public void onClick(View v) {
        if (itemOnClickListener != null && v.getTag() != null)
            itemOnClickListener.onItemClick(v, (Integer) v.getTag());
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvBookTitle, tvAuthor, tvPublisher, tvCallno;
        private ImageView ivCover;


        private ViewHolder(View view) {
            super(view);
            tvBookTitle = view.findViewById(R.id.tv_title_item_search_book);
            tvAuthor = view.findViewById(R.id.tv_author_item_search_book);
            tvPublisher = view.findViewById(R.id.tv_publisher_item_search_book);
            tvCallno = view.findViewById(R.id.tv_callno_item_search_book);
            ivCover = view.findViewById(R.id.iv_cover_item_search_book);

        }
    }


    public interface ItemOnClickListener {
        void onItemClick(View view, int position);
    }


}
