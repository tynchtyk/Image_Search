package com.example.image_search.View.Adapters;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.image_search.R;
import com.example.image_search.Service.Model.ImageDescription;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class SearchImageListAdapter extends RecyclerView.Adapter<SearchImageListAdapter.ViewHolder> {
    private List<ImageDescription> items;
    Context context;

    public SearchImageListAdapter(Context context, ArrayList<ImageDescription> images) {
        this.context = context;
        this.items = images;
    }
    @NonNull
    @Override
    public SearchImageListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchImageListAdapter.ViewHolder holder, int position) {
        holder.onBind(holder.itemView.getContext(), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        View layoutContent;
        TextView tvCollection;
        TextView tvDatetime;
        ImageView ivThumb;

        ViewHolder(@NonNull View view) {
            super(view);

            ivThumb = view.findViewById(R.id.iv_thumb);
            tvCollection = view.findViewById(R.id.tv_collection);
            tvDatetime = view.findViewById(R.id.tv_date);
            layoutContent = view.findViewById(R.id.layout_content);
        }

        void onBind(Context context, int position) {
            ImageDescription item = items.get(position);

            // set image
            int placeholderColor = context.getResources().getColor(R.color.white);
            Picasso.get().load(item.getImage()).into(ivThumb);

            // set collection
            if (!TextUtils.isEmpty(item.getCollection())) {
                tvCollection.setText(item.getCollection());
                tvCollection.setVisibility(View.VISIBLE);
            } else {
                tvCollection.setVisibility(View.GONE);
            }

            // set datetime
            if (!TextUtils.isEmpty(item.getDatetime())) {
                tvDatetime.setText(item.getDatetime());
                tvDatetime.setVisibility(View.VISIBLE);
            } else {
                tvDatetime.setVisibility(View.GONE);
            }

            // set layout content visibility
            if (tvCollection.getVisibility() == View.VISIBLE || tvDatetime.getVisibility() == View.VISIBLE) {
                layoutContent.setVisibility(View.VISIBLE);
            } else {
                layoutContent.setVisibility(View.GONE);
            }


        }
    }
}