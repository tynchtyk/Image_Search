package com.example.image_search.View.Adapters;

import android.content.Context;
import android.os.Bundle;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class SearchImageListAdapter extends RecyclerView.Adapter<SearchImageListAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private List< ImageDescription> items;
    Context context;
    Fragment fragment;

    public SearchImageListAdapter(Context context,Fragment fragment, ArrayList<ImageDescription> items, OnItemClickListener onItemClickListener){
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
    public SearchImageListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchImageListAdapter.ViewHolder holder, int position) {
        holder.onBind(holder.itemView.getContext(), position);
        holder.ivThumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Bundle bundle = new Bundle();
                bundle.putString("url", items.get(position).getImage());
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                NavHostFragment.findNavController(fragment).navigate(R.id.action_image_search_to_image_detail3, bundle);
            }
        });
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
        /*else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return SearchImageListAdapter.this.items.size();
                }

                @Override
                public int getNewListSize() {
                    return items.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return false;
                }


                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    ImageDescription project = items.get(newItemPosition);
                    ImageDescription old = items.get(oldItemPosition);
                    return project.getImage() == old.id
                            && Objects.equals(project.git_url, old.git_url);
                }
            });
            this.projectList = projectList;
            result.dispatchUpdatesTo(this);
        }*/
        else {
            this.items.clear();
            if(imagetList == null){
                Log.e("IMAGELIST", "NULLLFUCKING");
            }
            this.items.addAll(imagetList);
            notifyDataSetChanged();
        }
    }
    public interface OnItemClickListener {
        public void onItemClick(ImageDescription imageDescription);
    }
}