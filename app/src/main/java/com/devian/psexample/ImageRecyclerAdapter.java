package com.devian.psexample;


import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.devian.network.Hits;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.MyViewHolder> {

    private List<Hits> hits;
    private Context mContext;
    private ImageClickListener mListener;
    int width = 100, height = 100;

    public ImageRecyclerAdapter(Context context, List<Hits> hits, ImageClickListener listener) {
        mContext = context;
        this.hits = hits;
        mListener = listener;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager=(WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        width = displayMetrics.widthPixels;
        width = width/3;
        height = width;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        MyViewHolder viewHolder=new MyViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Log.d("Picasso", "onBindViewHolder: " + position);
        ConstraintLayout.LayoutParams param=( ConstraintLayout.LayoutParams)holder.imageView.getLayoutParams();
        param.width=width;
        param.height=height;
        Picasso.with(mContext).load(hits.get(position).getWebformatURL()).placeholder(R.drawable.placeholder).fit().into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onImageClick(hits.get(position).getLargeImageURL());
            }
        });
    }

    @Override
    public int getItemCount() {
        return (hits != null) ? hits.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AnimationDrawable animationDrawable;
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
    }
}
