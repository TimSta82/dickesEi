package com.example.tstaats.myvermeer.Fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tstaats.myvermeer.Common.Aktien;
import com.example.tstaats.myvermeer.Common.CustomDate;
import com.example.tstaats.myvermeer.Common.XYValues;
import com.example.tstaats.myvermeer.Dialogs.HanseBuySellDialogFragment;
import com.example.tstaats.myvermeer.Dialogs.LloydBuySellDialogFragment;
import com.example.tstaats.myvermeer.Dialogs.RoyalBuySellDialogFragment;
import com.example.tstaats.myvermeer.Dialogs.StarBuySellDialogFragment;
import com.example.tstaats.myvermeer.MainActivity;
import com.example.tstaats.myvermeer.Common.PlayerStats;
import com.example.tstaats.myvermeer.R;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class AktienFragment extends Fragment implements LloydBuySellDialogFragment.OnLloydSelected,
        StarBuySellDialogFragment.OnStarSelected,
        HanseBuySellDialogFragment.OnHanseSelected,
        RoyalBuySellDialogFragment.OnRoyalSelected{

    private static final String TAG = "AktienFragment";

    @Override
    public void changeLloydCount(int newLloydStock) {
        lloydCountTv.setText("Lloyd: " + newLloydStock);
        lloydValueTv.setText("Lloyd: " + mAktien.getLloyd() * newLloydStock + "€");
    }

    @Override
    public void changeStarCount(int newStarStock) {
        starCountTv.setText("Star: " + newStarStock);
        starValueTv.setText("Star: " + mAktien.getStar() * newStarStock + "€");
    }

    @Override
    public void changeHanseCount(int newHanseStock) {
        hanseCountTv.setText("Hanse: " + newHanseStock);
        hanseValueTv.setText("Hanse: " + mAktien.getHanse() * newHanseStock + "€");
    }

    @Override
    public void changeRoyalCount(int newRoyalStock) {
        royalCountTv.setText("Royal: " + newRoyalStock);
        royalValueTv.setText("Royal: " + mAktien.getRoyal() * newRoyalStock + "€");
    }

    private MainActivity mainActivity;
    private PlayerStats mPlayer;
    private Aktien mAktien;
    private CustomDate mDate;


    // widgets
    //private GraphView graphView;
    private TextView stockInfoTv;
    private TextView lloydCountTv;
    private TextView starCountTv;
    private TextView hanseCountTv;
    private TextView royalCountTv;
    private TextView lloydValueTv;
    private TextView starValueTv;
    private TextView hanseValueTv;
    private TextView royalValueTv;

    private Button lloydBtn;
    private Button starBtn;
    private Button hanseBtn;
    private Button royalBtn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aktien, container, false);

        initAktien(view);
        //displayGraph();

        GraphView graphView = (GraphView) view.findViewById(R.id.graphview);



        mainActivity.mSeriesLloyd.setColor(Color.BLUE);
        mainActivity.mSeriesLloyd.setTitle("Lloyd");
        mainActivity.mSeriesStar.setColor(Color.RED);
        mainActivity.mSeriesStar.setTitle("Star");
        mainActivity.mSeriesHanse.setColor(Color.YELLOW);
        mainActivity.mSeriesHanse.setTitle("Hanse");
        mainActivity.mSeriesRoyal.setColor(Color.BLACK);
        mainActivity.mSeriesRoyal.setTitle("Royal");
        graphView.getLegendRenderer().setVisible(true);
        graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graphView.addSeries(mainActivity.mSeriesLloyd);
        graphView.addSeries(mainActivity.mSeriesStar);
        graphView.addSeries(mainActivity.mSeriesHanse);
        graphView.addSeries(mainActivity.mSeriesRoyal);
        graphView.getViewport().setXAxisBoundsManual(false);
        //graph2.getViewport().setMinX(0);

        //graphView.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(mainActivity));
        graphView.getGridLabelRenderer().setNumHorizontalLabels(4);
//        graphView.getViewport().setMinX(mDate.getMonth()-3);
//        graphView.getViewport().setMaxX(mDate.getMonth());

        graphView.getViewport().setMinX(mainActivity.graphLastXValue);
        graphView.getViewport().setMaxX(mainActivity.graphLastXValue + 12);
        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScalableY(true);
        displayStockHolding();
        displayStockHoldingValue();

        lloydBtn.setOnClickListener(onClickListener);
        starBtn.setOnClickListener(onClickListener);
        hanseBtn.setOnClickListener(onClickListener);
        royalBtn.setOnClickListener(onClickListener);


        return view;

    }

    private void displayStockHolding() {
        lloydCountTv.setText("Lloyd: " + mPlayer.getPlayerLloyd());
        starCountTv.setText("Star: " + mPlayer.getPlayerStar());
        hanseCountTv.setText("Hanse: " + mPlayer.getPlayerHanse());
        royalCountTv.setText("Royal: " + mPlayer.getPlayerRoyal());
        //stockHoldingTv.setText("Anzahl: \n" + mPlayer.stockDetailsToString());
    }

    private void displayStockHoldingValue(){
        lloydValueTv.setText("Lloyd: " + mPlayer.getPlayerLloyd() * mAktien.getLloyd() + "€");
        starValueTv.setText("Star: " + mPlayer.getPlayerStar() * mAktien.getStar() + "€");
        hanseValueTv.setText("Hanse: " + mPlayer.getPlayerHanse() * mAktien.getHanse() + "€");
        royalValueTv.setText("Royal: " + mPlayer.getPlayerRoyal() * mAktien.getRoyal() + "€");

    }


    private void initAktien(View view) {

        //graphView = (GraphView) view.findViewById(R.id.graphview);
        stockInfoTv = view.findViewById(R.id.text_stock_info);
        stockInfoTv.setText("Aktienbestand von: " + mPlayer.getPlayerName());

        lloydCountTv = view.findViewById(R.id.text_lloyd_count);
        starCountTv = view.findViewById(R.id.text_star_count);
        hanseCountTv = view.findViewById(R.id.text_hanse_count);
        royalCountTv = view.findViewById(R.id.text_royal_count);

        lloydValueTv = view.findViewById(R.id.text_lloyd_value);
        starValueTv = view.findViewById(R.id.text_star_value);
        hanseValueTv = view.findViewById(R.id.text_hanse_value);
        royalValueTv = view.findViewById(R.id.text_royal_value);

        lloydBtn = view.findViewById(R.id.button_lloyd);
        starBtn = view.findViewById(R.id.button_star);
        hanseBtn = view.findViewById(R.id.button_hanse);
        royalBtn = view.findViewById(R.id.button_royal);


    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int buttonId = v.getId();

            switch (buttonId){
                case R.id.button_lloyd:
                    Log.d(TAG, "onClick: lloyd");
                    LloydBuySellDialogFragment lloydBuySellDialogFragment = new LloydBuySellDialogFragment();
                    lloydBuySellDialogFragment.setTargetFragment(AktienFragment.this, 3);
                    lloydBuySellDialogFragment.show(getFragmentManager(), "LloydBuySellDialogFragment");

                    break;
                case R.id.button_star:
                    Log.d(TAG, "onClick: star");
                    StarBuySellDialogFragment starBuySellDialogFragment = new StarBuySellDialogFragment();
                    starBuySellDialogFragment.setTargetFragment(AktienFragment.this, 3);
                    starBuySellDialogFragment.show(getFragmentManager(), "StarBuySellDialogFragment");
                    break;
                case R.id.button_hanse:
                    Log.d(TAG, "onClick: hanse");
                    HanseBuySellDialogFragment hanseBuySellDialogFragment = new HanseBuySellDialogFragment();
                    hanseBuySellDialogFragment.setTargetFragment(AktienFragment.this, 3);
                    hanseBuySellDialogFragment.show(getFragmentManager(), "HanseBuySellDialogFragment");
                    break;
                case R.id.button_royal:
                    Log.d(TAG, "onClick: royal");
                    RoyalBuySellDialogFragment royalBuySellDialogFragment = new RoyalBuySellDialogFragment();
                    royalBuySellDialogFragment.setTargetFragment(AktienFragment.this, 3);
                    royalBuySellDialogFragment.show(getFragmentManager(), "RoyalBuySellDialogFragment");
                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity)getActivity();
        mPlayer = PlayerStats.getInstance();
        mAktien = Aktien.getInstance();
        mDate = CustomDate.getInstance();
    }



}
