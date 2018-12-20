package com.example.tstaats.myvermeer.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tstaats.myvermeer.Common.Aktien;
import com.example.tstaats.myvermeer.Common.L;
import com.example.tstaats.myvermeer.MainActivity;
import com.example.tstaats.myvermeer.Common.PlayerStats;
import com.example.tstaats.myvermeer.R;

public class BankFragment extends Fragment{

    private static final String TAG = "BankFragment";

    private MainActivity mainActivity;
    private PlayerStats mPlayer;
    private Aktien mAktien;

    // widgets
    public TextView oldStockTv;
    public TextView currentStockTv;
    private Button stockBtn;
    private Button kontoBtn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bank, container, false);

        initBank(view);

        stockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.l(TAG, "stockBtn clicked");
                mainActivity.fragmentSwitcher(new AktienFragment(), "AktienFragment");
            }
        });

        kontoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.l(TAG, "kontoBtn clicked");
                mainActivity.fragmentSwitcher(new KontoFragment(), "KontoFragment");

            }
        });

        return view;

    }

    public void displayStock(){
        Log.d(TAG, "displayStock: ");
        String m = "Alter Kurs X: \nLloyd: " + mainActivity.mLloyd + "\nStar: " + mainActivity.mStar +
                "\nHanse: " + mainActivity.mHanse + "\nRoyal: " + mainActivity.mRoyal;
        oldStockTv.setText(m);
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity)getActivity();
        mPlayer = PlayerStats.getInstance();
        mAktien = Aktien.getInstance();
    }

    private void initBank(View view){
        oldStockTv = view.findViewById(R.id.text_stock_old);
        currentStockTv = view.findViewById(R.id.text_stock_new);
        stockBtn = view.findViewById(R.id.button_stock);
        kontoBtn = view.findViewById(R.id.button_konto);

        currentStockTv.setText("Aktueller Kurs: \n" + mAktien.toString());
        displayStock();
        //currentStockTv.setText("Aktueller Kurs: \n" + mainActivity.mKursListe.get(0));
        //oldStockTv.setText("Alter Kurs: \n" + mainActivity.mKursListe.get(0));
    }



}
