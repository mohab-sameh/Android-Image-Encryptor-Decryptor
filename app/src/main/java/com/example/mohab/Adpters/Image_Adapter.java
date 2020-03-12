package com.example.mohab.Adpters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohab.R;
import com.example.mohab.utils.CImage;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Image_Adapter extends RecyclerView.Adapter<Image_Adapter.Image_View_Holder> {
    public class Image_View_Holder extends RecyclerView.ViewHolder{
        public ImageView imageViewCard;
        public TextView textViewName;

        public Image_View_Holder(@NonNull View itemView) {
            super(itemView);

            imageViewCard = itemView.findViewById(R.id.image_view_card);
            textViewName = itemView.findViewById(R.id.text_view_name);
        }
    }
    private Context context;
    private List<CImage> images;

    public Image_Adapter(Context context, List<CImage> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public Image_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(context).inflate(R.layout.image_item,parent,false);
        return new Image_View_Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Image_View_Holder holder, int position) {
        holder.textViewName.setText(images.get(position).getName());
        Picasso.with(context).load(images.get(position).getImageUrl()).fit().centerCrop()
                .into(holder.imageViewCard);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}
