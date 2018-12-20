package com.example.tstaats.myvermeer.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.tstaats.myvermeer.Fragments.PlantFragment;
import com.example.tstaats.myvermeer.MainActivity;
import com.example.tstaats.myvermeer.R;

import java.util.List;

public class PlantFieldAdapter extends RecyclerView.Adapter<PlantFieldAdapter.FieldViewHolder> implements PlantFragment.OnResourceSelected{

    private static final String TAG = "PlantFieldAdapter";

    private MainActivity mainActivity;
    private Context mContext;
    private List<ImageButton> mButtonList;
    private List<Boolean> mIsCountedList;

    @Override
    public int changeResource(PlantFragment.PlantResource chosenRes) {
        switch (chosenRes){
            case NATURE:
                mPlantResource = R.drawable.ic_nature;
                break;
            case KAFFEE:
                mPlantResource = R.drawable.coffee;
                break;
            case TABAK:
                mPlantResource = R.drawable.tobacco;
                break;
            case KAKAO:
                mPlantResource = R.drawable.cocoa;
                break;
            case TEE:
                mPlantResource = R.drawable.tea1;
                break;
            default:
                mPlantResource = R.drawable.ic_nature;
                break;
        }

        return mPlantResource;
    }


    private int mPlantResource;

    private int counter = 0;

    public PlantFieldAdapter(Context context, List<ImageButton> buttonList, List<Boolean> isCountedList) {
        this.mContext = context;
        this.mButtonList = buttonList;
        this.mIsCountedList = isCountedList;
        mainActivity = (MainActivity)mContext;
    }

    @NonNull
    @Override
    public FieldViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.field_item_button, parent, false);
        return new FieldViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FieldViewHolder holder, final int position) {

        // maybe some changes are necessary
        holder.fieldImageBtn.setImageResource(mPlantResource);

        holder.fieldImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsCountedList.get(position) == false){
                    mIsCountedList.remove(position);
                    Boolean isCounted = true;
                    mIsCountedList.add(position, isCounted);
                    counter++;
                    holder.fieldImageBtn.setImageResource(R.drawable.ic_kaffee);
                    Log.d(TAG, "onClick: counter: " + counter);
                } else {
                    Log.d(TAG, "onClick: button is allready counted");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mButtonList.size();
    }




    public static class FieldViewHolder extends RecyclerView.ViewHolder{

        ImageButton fieldImageBtn;
        CardView fieldCardview;


        public FieldViewHolder(View itemView){
            super(itemView);

            fieldImageBtn = (ImageButton) itemView.findViewById(R.id.imagebutton_field);
            fieldCardview = (CardView) itemView.findViewById(R.id.cardview_field_item);
        }
    }
}
