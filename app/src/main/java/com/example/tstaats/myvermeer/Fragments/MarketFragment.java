package com.example.tstaats.myvermeer.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tstaats.myvermeer.Common.PlayerStats;
import com.example.tstaats.myvermeer.Common.RawMaterials;
import com.example.tstaats.myvermeer.MainActivity;
import com.example.tstaats.myvermeer.R;

public class MarketFragment extends Fragment {

    private static final String TAG = "MarketFragment";

    private MainActivity mainActivity;
    private PlayerStats mPlayer;
    private RawMaterials mRawMaterials;

    // widgets
    private TextView amountTabakTv;
    private TextView amountTeeTv;
    private TextView amountKaffeeTv;
    private TextView amountKakaoTv;

    private TextView oldValueResourcesTv;
    private TextView currValueResourcesTv;

/*
    private TextView oldValueTabakTv;
    private TextView oldValueTeeTv;
    private TextView oldValueKaffeeTv;
    private TextView oldValueKakaoTv;

    private TextView currValueTabakTv;
    private TextView currValueTeeTv;
    private TextView currValueKaffeeTv;
    private TextView currValueKakaoTv;
*/

    private Button sellTabakBtn;
    private Button sellTeeBtn;
    private Button sellKaffeeBtn;
    private Button sellKakaoBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_market, container, false);

        initMarket(view);
        displayResourceAmount();

        sellTeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: tee");
            }
        });

        sellTabakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: tabak");
            }
        });

        sellKakaoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: kakao");
            }
        });

        sellKaffeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: kaffee");
            }
        });

        return view;
    }

    private void displayResourceAmount() {
        amountTabakTv.setText("Tabak: " + mPlayer.getPlayerTabak());
        amountTeeTv.setText("Tee: " + mPlayer.getPlayerTee());
        amountKaffeeTv.setText("Kaffee: " + mPlayer.getPlayerKaffee());
        amountKakaoTv.setText("Kakao: " + mPlayer.getPlayerKakao());
    }

    private void initMarket(View view) {

        amountTabakTv = view.findViewById(R.id.text_tabak);
        amountTeeTv = view.findViewById(R.id.text_tee);
        amountKaffeeTv = view.findViewById(R.id.text_kaffee);
        amountKakaoTv = view.findViewById(R.id.text_kakau);

        oldValueResourcesTv = view.findViewById(R.id.text_oldvalue_resources);
        currValueResourcesTv = view.findViewById(R.id.text_currvalue_resources);

        currValueResourcesTv.setText("Aktueller Preis: \n" + mRawMaterials.toString());
        displayResourceValues();
/*
        oldValueTabakTv = view.findViewById(R.id.text_oldvalue_tabak);
        oldValueTeeTv = view.findViewById(R.id.text_oldvalue_tee);
        oldValueKaffeeTv = view.findViewById(R.id.text_oldvalue_kaffee);
        oldValueKakaoTv = view.findViewById(R.id.text_oldvalue_kakau);

        currValueTabakTv = view.findViewById(R.id.text_currentvalue_tabak);
        currValueTeeTv = view.findViewById(R.id.text_currentvalue_tee);
        currValueKaffeeTv = view.findViewById(R.id.text_currentvalue_kaffee);
        currValueKakaoTv = view.findViewById(R.id.text_currentvalue_kakau);
*/

        sellKaffeeBtn = view.findViewById(R.id.button_sell_kaffee);
        sellTabakBtn = view.findViewById(R.id.button_sell_tabak);
        sellKakaoBtn = view.findViewById(R.id.button_sell_kakao);
        sellTeeBtn = view.findViewById(R.id.button_sell_tee);
    }

    private void displayResourceValues() {
        Log.d(TAG, "displayResourceValues: is called");
        String m = "Alter Kurs: \nTabak: " + mainActivity.mTabak + "\nTee: " + mainActivity.mTee + "\nKaffee: " + mainActivity.mKaffee + "\nKakao: " + mainActivity.mKakao + "\n";
        oldValueResourcesTv.setText(m);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity) getActivity();

        mPlayer = PlayerStats.getInstance();
        mRawMaterials = RawMaterials.getInstance();
    }
}
