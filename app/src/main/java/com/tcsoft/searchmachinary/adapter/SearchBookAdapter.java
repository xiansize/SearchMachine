package com.tcsoft.searchmachinary.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.tcsoft.searchmachinary.R;
import com.tcsoft.searchmachinary.bean.Book;
import java.util.List;

/**
 * Created by Admin on 2019/6/17.
 */

public class SearchBookAdapter extends RecyclerView.Adapter<SearchBookAdapter.ViewHolder> {

    private List<Book> list;
    private LayoutInflater layoutInflater;
    private Context context;

    public SearchBookAdapter(List<Book> list, Context context) {
        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_item_search_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvBookTitle.setText(list.get(position).getTitle());
        holder.tvAuthor.setText(list.get(position).getAuthor());
        holder.tvPublisher.setText(list.get(position).getPublisher());
        holder.tvCallno.setText(list.get(position).getCallNo());

        Picasso.with(context)
                .load(list.get(position).getCover())
                .into(holder.ivCover);
    }

    @Override
    public int getItemCount() {
        return list.size();
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


}
