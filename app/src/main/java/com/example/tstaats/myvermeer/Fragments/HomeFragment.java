package com.example.tstaats.myvermeer.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tstaats.myvermeer.Common.Aktien;
import com.example.tstaats.myvermeer.Common.City;
import com.example.tstaats.myvermeer.Common.CustomDate;
import com.example.tstaats.myvermeer.Common.L;
import com.example.tstaats.myvermeer.MainActivity;
import com.example.tstaats.myvermeer.Common.PlayerStats;
import com.example.tstaats.myvermeer.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment{

    private static final String TAG = "HomeFragment";

    private MainActivity mainActivity;
    private CustomDate mCustomDate;
    private PlayerStats mPlayer;
    private Aktien mAktien;

    public City mCity;
    public ArrayList<City> mCityList;

    public ImageView mCityIv;
    private Button mMarktBtn;
    private Button mCasinoBtn;
    private Button mGallerieBtn;
    private Button mAuktionBtn;
    private Button mOverviewBtn;
    private Button mPlantageBtn;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Log.d(TAG, "onCreateView: is called");
        createCityList();
        initHomeFragment(view);

        for (City city: mCityList) {
            if (city.getCityName().equals(mPlayer.getCurrentLocation().getCityName())){
                mCityIv.setImageResource(city.getCityImg());
            }
        }

        return view;
    }

    public void createCityList(){
        mCityList = new ArrayList<>();
        mCity = new City(mainActivity);
        mCity.setListOfCities();
        mCityList = mCity.getListOfCities();

    }

    private void initHomeFragment(View view) {

        Log.d(TAG, "initHomeFragment: is called");
        mCityIv = view.findViewById(R.id.image_home_city);
        mMarktBtn = view.findViewById(R.id.button_market);
        mAuktionBtn = view.findViewById(R.id.button_auktion);
        mCasinoBtn = view.findViewById(R.id.button_casino);
        mGallerieBtn = view.findViewById(R.id.button_gallery);
        mOverviewBtn = view.findViewById(R.id.button_overview);
        mPlantageBtn = view.findViewById(R.id.button_plantage);


        if (mPlayer.getCurrentLocation() == null){
            mPlayer.setCurrentLocation(new City(mCityList.get(0).getCurrentTownInfo(),mCityList.get(0).getCityName(), mCityList.get(0).getCityImg(), mCityList.get(0).getCountryName(),
                    mCityList.get(0).getCountryFlag(), mCityList.get(0).getPoliticalStatus(), mCityList.get(0).getPlant(),
                    mCityList.get(0).getLatitude(), mCityList.get(0).getLongitude()));
            mainActivity.displayCityName(mPlayer.getCurrentLocation().getCityName());
        }

        if (mPlayer.getCurrentLocation().getCurrentTownInfo().toString().equals("CAPITAL")){
            mGallerieBtn.setVisibility(View.VISIBLE);
            mMarktBtn.setVisibility(View.VISIBLE);
            mAuktionBtn.setVisibility(View.VISIBLE);
            mCasinoBtn.setVisibility(View.VISIBLE);
            mOverviewBtn.setVisibility(View.VISIBLE);
            mPlantageBtn.setVisibility(View.GONE);
        } else if (mPlayer.getCurrentLocation().getCurrentTownInfo().toString().equals("VILLAGE")){
            mGallerieBtn.setVisibility(View.GONE);
            mMarktBtn.setVisibility(View.GONE);
            mAuktionBtn.setVisibility(View.GONE);
            mCasinoBtn.setVisibility(View.GONE);
            mOverviewBtn.setVisibility(View.VISIBLE);
            mPlantageBtn.setVisibility(View.VISIBLE);
        } else if (mPlayer.getCurrentLocation().getCurrentTownInfo().toString().equals("CITY")){
            mGallerieBtn.setVisibility(View.VISIBLE);
            mMarktBtn.setVisibility(View.GONE);
            mAuktionBtn.setVisibility(View.VISIBLE);
            mCasinoBtn.setVisibility(View.VISIBLE);
            mOverviewBtn.setVisibility(View.VISIBLE);
            mPlantageBtn.setVisibility(View.GONE);
        }

        mOverviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //L.t(mainActivity, "mOverviewBtn clicked");
                Log.d(TAG, "onCreateView: getPlayerArtList(): " + mPlayer.getPlayerArtList());
                mainActivity.fragmentSwitcher(new OverviewFragment(), "OverviewFragment");

            }
        });

        mPlantageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //L.t(mainActivity, "mPlantageBtn clicked");
                mainActivity.fragmentSwitcher(new PlantFragment(), "PlantFragment");
            }
        });

        mMarktBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //L.t(mainActivity, "MarktBtn clicked");
                mainActivity.fragmentSwitcher(new MarketFragment(), "MarketFragment");
            }
        });

        mAuktionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //L.t(mainActivity, "AuktionBtn clicked");
                mainActivity.fragmentSwitcher(new AuktionFragment(), "AuktionFragment");
            }
        });

        mGallerieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //L.t(mainActivity, "GallerieBtn clicked");
                mainActivity.fragmentSwitcher(new GalleryFragment(), "GalleryFragment");
            }
        });

        mCasinoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //L.t(mainActivity, "CasinoBtn clicked");
                mainActivity.fragmentSwitcher(new DiceFragment(), "DiceFragment");
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity)getActivity();
        mPlayer = PlayerStats.getInstance();
        mCustomDate = CustomDate.getInstance();
        mAktien = Aktien.getInstance();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (mPlayer != null){

        }
    }
}
