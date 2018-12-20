package com.example.tstaats.myvermeer.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tstaats.myvermeer.Adapters.CityAdapter;
import com.example.tstaats.myvermeer.Common.Aktien;
import com.example.tstaats.myvermeer.Common.City;
import com.example.tstaats.myvermeer.Common.CustomDate;
import com.example.tstaats.myvermeer.Dialogs.StayDialogFragment;
import com.example.tstaats.myvermeer.MainActivity;
import com.example.tstaats.myvermeer.Common.PlayerStats;
import com.example.tstaats.myvermeer.R;

import java.util.ArrayList;

public class TravelFragment extends Fragment implements StayDialogFragment.OnInputSelected{

    private static final String TAG = "TravelFragment";

    @Override
    public void sendInput(int timeSpan) {
        mainActivity.calendar(timeSpan);
    }

    private MainActivity mainActivity;
    private ArrayList<City> mListOfCities;
    private CityAdapter mAdapter;
    private PlayerStats mPlayer;
    private Aktien mAktien;
    private CustomDate mCustomDate;
    private City mCity;

    // widgets
    private ImageView mCurrentFlagIv;
    private TextView mCurrentCityTv;
    private TextView mCurrentCountryTv;
    private Spinner mSpinnerCities;
    private Button mJourneyBtn;
    private Button mStayBtn;
    private ImageView markerHH, markerNY, markerROM;

    // vars
    private String clickedCityName;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_travel, container, false);

        initTravel(view);
        showCurrentLocationMarker();


        clickedCityName = "";
        mSpinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                City clickedItem = (City) parent.getItemAtPosition(position);
                clickedCityName = clickedItem.getCityName();
                switch (clickedCityName){
                    case "Hamburg":
                        markerHH.setVisibility(View.VISIBLE);
                        break;
                    case "New York":
                        markerHH.setVisibility(View.VISIBLE);
                        break;
                    case "Rom":
                        markerHH.setVisibility(View.VISIBLE);
                        break;
                }
                //Toast.makeText(mainActivity, clickedCityName + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        
        mJourneyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Order Btn clicked");

                for (int i = 0; i < mListOfCities.size() ; i++) {
                    if(mListOfCities.get(i).getCityName().equals(clickedCityName)){

                        City originCity = new City(mPlayer.getCurrentLocation().getCurrentTownInfo(), mPlayer.getCurrentLocation().getCityName(), mPlayer.getCurrentLocation().getCityImg(), mPlayer.getCurrentLocation().getCountryName(),
                                mPlayer.getCurrentLocation().getCountryFlag(), mPlayer.getCurrentLocation().getPoliticalStatus(), mPlayer.getCurrentPlant(),
                                mPlayer.getCurrentLocation().getLatitude(), mPlayer.getCurrentLocation().getLongitude());

                        City destinationCity = new City(mListOfCities.get(i).getCurrentTownInfo(), mListOfCities.get(i).getCityName(), mPlayer.getCurrentLocation().getCityImg(), mListOfCities.get(i).getCountryName(),
                                mListOfCities.get(i).getCountryFlag(), mListOfCities.get(i).getPoliticalStatus(), mListOfCities.get(i).getPlant(),
                                mListOfCities.get(i).getLatitude(), mListOfCities.get(i).getLongitude());

                        mPlayer.setCurrentLocation(destinationCity);
                        mListOfCities.add(originCity);
                        mListOfCities.remove(destinationCity);

                        mainActivity.displayCityName(destinationCity.getCityName());

                        mainActivity.fragmentSwitcher(new MeantimeFragment(), "MeantimeFragment");

                    }
                }
            }
        });
        
        mStayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: stay Btn clicked");
                StayDialogFragment stayDialogFragment = new StayDialogFragment();
                stayDialogFragment.setTargetFragment(TravelFragment.this, 3);
                stayDialogFragment.show(getFragmentManager(), "StayDialogFragment");
            }
        });

        return view;
    }

    private void showCurrentLocationMarker() {
        String cityName = mPlayer.getCurrentLocation().getCityName();
        switch (cityName){
            case "Hamburg":
                markerHH.setVisibility(View.VISIBLE);
                break;
            case "New York":
                markerHH.setVisibility(View.VISIBLE);
                break;
            case "Rom":
                markerHH.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void initTravel(View view) {

        markerHH = view.findViewById(R.id.imageView_hamburg);
        markerNY = view.findViewById(R.id.imageView_newyork);
        markerROM = view.findViewById(R.id.imageView_rom);
        mSpinnerCities = view.findViewById(R.id.spinner_countries);
        mCurrentFlagIv = view.findViewById(R.id.image_current_flag);
        mCurrentCityTv = view.findViewById(R.id.text_current_city);
        mCurrentCountryTv = view.findViewById(R.id.text_current_country);
        mJourneyBtn = view.findViewById(R.id.button_journey);
        mStayBtn = view.findViewById(R.id.button_stay);

        markerHH.setVisibility(View.GONE);
        markerROM.setVisibility(View.GONE);
        markerNY.setVisibility(View.GONE);

        mCity = new City(mainActivity);

        mListOfCities = new ArrayList<>();
        //City.setListOfCities(mListOfCities);
        mCity.setListOfCities();
        mListOfCities = mCity.getListOfCities();
        for (int i = 0; i < mListOfCities.size(); i++) {
            if(mListOfCities.get(i).getCityName().equals(mPlayer.getCurrentLocation().getCityName())){
                mListOfCities.remove(i);
            }
        }

        mCurrentFlagIv.setImageResource(mPlayer.getCurrentLocation().getCountryFlag());
        mCurrentCityTv.setText(mPlayer.getCurrentLocation().getCityName());
        mCurrentCountryTv.setText(mPlayer.getCurrentLocation().getCountryName());

        mAdapter = new CityAdapter(getActivity(), mListOfCities);
        mSpinnerCities.setAdapter(mAdapter);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity)getActivity();

        mAktien = Aktien.getInstance();
        mCustomDate = CustomDate.getInstance();
        mPlayer = PlayerStats.getInstance();
    }


}
