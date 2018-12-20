package com.example.tstaats.myvermeer.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.tstaats.myvermeer.Adapters.PlantFieldAdapter;
import com.example.tstaats.myvermeer.Common.Aktien;
import com.example.tstaats.myvermeer.Common.City;
import com.example.tstaats.myvermeer.Common.FieldItem;
import com.example.tstaats.myvermeer.Common.Plant;
import com.example.tstaats.myvermeer.Common.PlayerStats;
import com.example.tstaats.myvermeer.Common.RawMaterials;
import com.example.tstaats.myvermeer.MainActivity;
import com.example.tstaats.myvermeer.R;

import java.util.ArrayList;
import java.util.List;

public class PlantFragment extends Fragment {

    private static final String TAG = "PlantFragment";
    public static final int SPAN_COUNT = 7;

    public interface OnResourceSelected{
        int changeResource(PlantResource chosenRes);
    }
    public OnResourceSelected mOnResourceSelected;

    private MainActivity mainActivity;
    private City mCity;
    private PlayerStats mPlayer;
    private Aktien mAktien;
    private RawMaterials mRawMaterials;

    // widgets
    private RadioGroup radioGroup;
    private RadioButton radioBtn;
    // R.layout.fragment_plant2
    private ImageView fieldIv1;
    private ImageView fieldIv2;
    private ImageView fieldIv3;
    private ImageView fieldIv4;
    private int mPlantResource;

    private ImageView ivPlantage;
    private Button btnBuyPlantage;

    public enum PlantResource {TABAK, TEE, KAFFEE, KAKAO, NATURE}
    public PlantResource choosenResource;
    //private List<FieldItem> fieldItemList;
    private List<ImageButton> fieldItemList;
    private List<Boolean> fieldItemIsCountedList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        //View view = inflater.inflate(R.layout.fragment_plant2, container, false);
        View view = inflater.inflate(R.layout.fragment_plant, container, false);

        initPlant(view);

        for (int i = 0; i < mCity.getListOfCities().size(); i++) {
            mCity.getListOfCities().get(i);
        }


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rbtn_kaffee:
                        choosenResource = PlantResource.KAFFEE;
                        mPlantResource = R.drawable.coffee;
                        //mOnResourceSelected.changeResource(choosenResource);
                        //mainActivity.setChoosenResource(choosenResource);
                        Log.d(TAG, "onCheckedChanged: kaffee");
                        break;
                    case R.id.rbtn_kakau:
                        choosenResource = PlantResource.KAKAO;
                        mPlantResource = R.drawable.cocoa;
                        //mOnResourceSelected.changeResource(choosenResource);
                        Log.d(TAG, "onCheckedChanged: KAKAO");
                        break;
                    case R.id.rbtn_tabak:
                        choosenResource = PlantResource.TABAK;
                        mPlantResource = R.drawable.tobacco;
                        //mOnResourceSelected.changeResource(choosenResource);
                        Log.d(TAG, "onCheckedChanged: TABAK");
                        break;
                    case R.id.rbtn_tee:
                        choosenResource = PlantResource.TEE;
                        mPlantResource = R.drawable.tea1;
                        //mOnResourceSelected.changeResource(choosenResource);
                        Log.d(TAG, "onCheckedChanged: TEE");
                        break;
                    case R.id.rbtn_renature:
                        choosenResource = PlantResource.NATURE;
                        mPlantResource = R.drawable.ic_nature;
                        //mOnResourceSelected.changeResource(choosenResource);
                        Log.d(TAG, "onCheckedChanged: NATURE");
                        break;
                    default:
                        choosenResource = PlantResource.NATURE;
                        mPlantResource = R.drawable.ic_nature;
                        //mOnResourceSelected.changeResource(choosenResource);
                        Log.d(TAG, "onCheckedChanged: NATURE");
                        break;
                }
            }
        });


/*
        fieldIv1.setOnClickListener(listener);
        fieldIv2.setOnClickListener(listener);
        fieldIv3.setOnClickListener(listener);
        fieldIv4.setOnClickListener(listener);
*/



        // R.layout.fragment_plant

        fieldItemList = new ArrayList<>();
        fieldItemList = mainActivity.createFieldItemList();
        fieldItemIsCountedList = new ArrayList<>();
        fieldItemIsCountedList = mainActivity.createFieldItemIsCountedList();
        RecyclerView myrv = (RecyclerView) view.findViewById(R.id.recyclerview_plant);
        PlantFieldAdapter adapter = new PlantFieldAdapter(getActivity(), fieldItemList, fieldItemIsCountedList);
        myrv.setLayoutManager(new GridLayoutManager(getActivity(), SPAN_COUNT));
        myrv.setAdapter(adapter);


        return view;
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String n = v.getTag().toString();
            switch (n){
                case "feld1":
                    fieldIv1.setImageResource(mPlantResource);
                    break;
                case "feld2":
                    fieldIv2.setImageResource(mPlantResource);
                    break;
                case "feld3":
                    fieldIv3.setImageResource(mPlantResource);
                    break;
                case "feld4":
                    fieldIv4.setImageResource(mPlantResource);
                    break;
            }
            Log.d(TAG, "onClick: tag: " + n);
        }
    };


    private void initPlant(View view) {

        radioGroup = view.findViewById(R.id.rg_rgrp);
        choosenResource = PlantResource.KAFFEE;

        ivPlantage = view.findViewById(R.id.image_plantage);
        btnBuyPlantage = view.findViewById(R.id.button_buy_plantage);

        btnBuyPlantage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mainActivity, "gekauft", Toast.LENGTH_SHORT).show();
                mPlayer.addPlantToPlayerPlantList(new Plant(mainActivity, mPlayer.getCurrentLocation().getCityName()));
                //mPlayer.getCurrentPlant().setBought(true);
                ivPlantage.setVisibility(View.GONE);
                btnBuyPlantage.setVisibility(View.GONE);
            }
        });
/*
        fieldIv1 = view.findViewById(R.id.image_field1);
        fieldIv2 = view.findViewById(R.id.image_field2);
        fieldIv3 = view.findViewById(R.id.image_field3);
        fieldIv4 = view.findViewById(R.id.image_field4);
*/
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity)getActivity();
        mPlayer = PlayerStats.getInstance();
        mAktien = Aktien.getInstance();
        mRawMaterials = RawMaterials.getInstance();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mOnResourceSelected = (OnResourceSelected) getTargetFragment();
        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage());
        }
    }
}
