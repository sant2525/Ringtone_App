package com.devloper.ringtone_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devloper.ringtone_app.R;
import com.devloper.ringtone_app.SecondActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryRingAdapter extends RecyclerView.Adapter<CategoryRingAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Upload> categoryList;

    public CategoryRingAdapter(Context context, List<Upload> categoryList) {
        this.mContext = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(mContext).inflate(R.layout.image_item, viewGroup,false);
        return  new ImageViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull CategoryRingAdapter.ImageViewHolder imageViewHolder, int i) {
        Upload c = categoryList.get(i);
        imageViewHolder.img_description.setText(c.imgName);
        Picasso.get()
                .load(c.imgUrl)
                .placeholder(R.drawable.imagepreview)
                .fit()
                .centerCrop()
                .into(imageViewHolder.image_view);


    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView img_description;
        public ImageView image_view;
        public RelativeLayout catCard;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            img_description = itemView.findViewById(R.id.img_description);
            image_view = itemView.findViewById(R.id.image_view);
            catCard = itemView.findViewById(R.id.cat_card);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            int p = getAdapterPosition();
            Upload c = categoryList.get(p);

            Intent intent = new Intent(mContext, SecondActivity.class);
            intent.putExtra("category", c.imgName);

            mContext.startActivity(intent);

        }
    }

}