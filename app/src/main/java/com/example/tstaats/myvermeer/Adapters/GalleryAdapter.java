package com.example.tstaats.myvermeer.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tstaats.myvermeer.Fragments.KunstwerkDetailFragment;
import com.example.tstaats.myvermeer.Common.Kunstwerk;
import com.example.tstaats.myvermeer.MainActivity;
import com.example.tstaats.myvermeer.R;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {

    private static final String TAG = "GalleryAdapter";

    private Context mContext;
    private MainActivity mainActivity;
    private List<Kunstwerk> mData;


    public GalleryAdapter(Context mContext, List<Kunstwerk> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mainActivity = (MainActivity)mContext;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.artThumbIv.setImageResource(mData.get(position).getImageId());
        holder.artistTv.setText(mData.get(position).getArtist());

        // set click listener
        holder.artCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: item clicked");
                Log.d(TAG, "onClick: Künstler: " + mData.get(position).getArtist() +
                        "\nTitel: " + mData.get(position).getImageName());
/*
                Toast.makeText(mContext, "Künstler: " + mData.get(position).getArtist() +
                        "\nTitel: " + mData.get(position).getImageName(), Toast.LENGTH_SHORT).show();
*/

                Bundle args = new Bundle();
                args.putString("artistName", mData.get(position).getArtist());
                args.putString("imageName", mData.get(position).getImageName());
                args.putInt("imageId", mData.get(position).getImageId());
                args.putInt("imageValue", mData.get(position).getImageValue());
                KunstwerkDetailFragment kunstwerkDetailFragment = new KunstwerkDetailFragment();
                kunstwerkDetailFragment.setArguments(args);
                mainActivity.fragmentSwitcher(kunstwerkDetailFragment, "KunstwerkDetailFragment");

            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView artistTv;
        ImageView artThumbIv;
        CardView artCard;

        public MyViewHolder(View itemView) {
            super(itemView);
            
            artCard = (CardView) itemView.findViewById(R.id.cardview_art); 
            artistTv = itemView.findViewById(R.id.text_artist);
            artThumbIv = itemView.findViewById(R.id.image_art);
        }
    }
}
