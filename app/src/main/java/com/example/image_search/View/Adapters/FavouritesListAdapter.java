package com.example.image_search.View.Adapters;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.image_search.R;
import com.example.image_search.Service.Entity.ImageDescription;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class FavouritesListAdapter extends RecyclerView.Adapter<FavouritesListAdapter.ViewHolder>{
    private OnItemClickListener onItemClickListener;
    private List<ImageDescription> items;
    Context context;
    Fragment fragment;

    public FavouritesListAdapter(Context context,Fragment fragment, ArrayList<ImageDescription> items, OnItemClickListener onItemClickListener){
        this.context = context;
        this.fragment = fragment;
        if(items == null) {
            this.items = new ArrayList<ImageDescription>();
        }
        else {
            this.items = items;
        }
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public FavouritesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_image, parent, false);
        return new FavouritesListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesListAdapter.ViewHolder holder, int position) {
        holder.onBind(holder.itemView.getContext(), position);
        holder.toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(items.get(position));
            }
        });
        if(items.get(position).getIsFavourite() == false)
            holder.toggleButton.setImageResource(R.drawable.ic_favourite_border);
        else
            holder.toggleButton.setImageResource(R.drawable.ic_favourite);

    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        View layoutContent;
        TextView tvDisplay_SiteName;
        ImageView ivThumb;
        ImageView toggleButton;

        ViewHolder(@NonNull View view) {
            super(view);

            ivThumb = view.findViewById(R.id.iv_thumb);
            tvDisplay_SiteName = view.findViewById(R.id.tv_display_sitename);
            layoutContent = view.findViewById(R.id.layout_content);
            toggleButton = view.findViewById(R.id.toggle_button);

        }

        void onBind(Context context, int position) {
            ImageDescription item = items.get(position);

            // set image
            int placeholderColor = context.getResources().getColor(R.color.white);
            Picasso.get().load(item.getImage()).into(ivThumb);

            // set collection
            if (!TextUtils.isEmpty(item.getDisplay_sitename())) {
                tvDisplay_SiteName.setText(item.getDisplay_sitename());
                tvDisplay_SiteName.setVisibility(View.VISIBLE);
            } else {
                tvDisplay_SiteName.setVisibility(View.GONE);
            }

            // set layout content visibility
            if (tvDisplay_SiteName.getVisibility() == View.VISIBLE) {
                layoutContent.setVisibility(View.VISIBLE);
            } else {
                layoutContent.setVisibility(View.GONE);
            }

            if (toggleButton.getVisibility() == View.VISIBLE) {
                layoutContent.setVisibility(View.VISIBLE);
            } else {
                layoutContent.setVisibility(View.GONE);
            }

        }
    }

    public void setImageList(List< ImageDescription> imagetList) {
        if (this.items == null) {
            this.items = imagetList;
            notifyDataSetChanged();
        }
        else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return FavouritesListAdapter.this.items.size();
                }

                @Override
                public int getNewListSize() {
                    return items.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return FavouritesListAdapter.this.items.get(oldItemPosition).getId().equals(
                            items.get(newItemPosition).getId()
                    );
                }


                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    ImageDescription newItem = items.get(newItemPosition);
                    ImageDescription oldItem = items.get(oldItemPosition);
                    return oldItem.getIsFavourite() == newItem.getIsFavourite() &&
                            oldItem.getHeight() == newItem.getHeight() &&
                            oldItem.getWidth() == newItem.getWidth() &&
                            oldItem.getThumbnail_url().compareTo(newItem.getThumbnail_url()) == 0 &&
                            oldItem.getImage().compareTo(newItem.getImage()) == 0 &&
                            oldItem.getDatetime().compareTo(newItem.getDatetime()) == 0 &&
                            oldItem.getDisplay_sitename().compareTo(newItem.getDisplay_sitename()) == 0 &&
                            oldItem.getId().compareTo(newItem.getId()) == 0;
                }
            });
            this.items = imagetList;
            result.dispatchUpdatesTo(this);
            notifyDataSetChanged();
        }/*
        else {
            this.items.clear();
            if(imagetList == null){
                Log.e("IMAGELIST", "NULLLFUCKING");
            }
            this.items.addAll(imagetList);
            notifyDataSetChanged();
        }*/
    }
    public interface OnItemClickListener {
        public void onItemClick(ImageDescription imageDescription);
    }
}
