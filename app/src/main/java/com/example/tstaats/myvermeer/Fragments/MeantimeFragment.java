package com.example.tstaats.myvermeer.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tstaats.myvermeer.Common.CustomDate;
import com.example.tstaats.myvermeer.Dialogs.StayDialogFragment;
import com.example.tstaats.myvermeer.MainActivity;
import com.example.tstaats.myvermeer.R;
import com.example.tstaats.myvermeer.Common.TickerNews;

import java.util.HashMap;

public class MeantimeFragment extends Fragment implements StayDialogFragment.OnInputSelected {

    private static final String TAG = "MeantimeFragment";

    @Override
    public void sendInput(int timeSpan) {

        Log.d(TAG, "sendInput: called");
        long dauer = (long) timeSpan;
        dauer = dauer * 100;
        new CountDownTimer(dauer, 100){


            @Override
            public void onTick(long millisUntilFinished) {

                //okBtn.setVisibility(View.INVISIBLE);
                //hideButtons();
            }

            @Override
            public void onFinish() {
                okBtn.setVisibility(View.VISIBLE);
                //displayButtons();
            }
        }.start();
        //startTimer(timeSpan);
    }

    private MainActivity mainActivity;
    private CustomDate mCustomDate;

    private Button okBtn;
    private TextView mTickerTv;

    private boolean isReady = false;
    private HashMap<String, TickerNews> mTickerNews;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_meantime, container, false);


        initMeantime(view);
        initTickerNews();


        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: oktBtn clicked");
                mainActivity.fragmentSwitcher(new HomeFragment(), "HomeFragment");
            }
        });
        return view;
    }

    private void startTimer(int timeInput){

        long dauer = (long) timeInput;
        dauer = dauer * 100;
        new CountDownTimer(dauer, 100){


            @Override
            public void onTick(long millisUntilFinished) {
                hideButtons();
            }

            @Override
            public void onFinish() {
                displayButtons();
            }
        }.start();
    }

    private void hideButtons() {

        Log.d(TAG, "hideButtons: called");
        okBtn.setVisibility(View.INVISIBLE);
        mainActivity.mBottomNavigationView.setVisibility(View.INVISIBLE);
    }

    private void displayButtons(){
        Log.d(TAG, "displayButtons: called");
        okBtn.setVisibility(View.VISIBLE);
        mainActivity.mBottomNavigationView.setVisibility(View.VISIBLE);
    }

    private void initMeantime(View view) {

        okBtn = view.findViewById(R.id.button_meantime_ok);
        mTickerTv = view.findViewById(R.id.text_tickernews);
        //hideButtons();
    }

    private void initTickerNews(){

        mTickerNews = new HashMap<>();
        mTickerNews.put("25.01.1950", new TickerNews("01.02.1950","Chinesische Truppen marschieren in Tibet ein."));
        mTickerNews.put("01.03.1950", new TickerNews("01.03.1950","Gründung der DDR am 7. Oktober."));
        mTickerNews.put("01.04.1950", new TickerNews("01.04.1950", "Ein Volksaufstand in Ungarn (23./24. Oktober) wird von den Sowjets blutig niedergeschlagen."));

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: called");

        mainActivity = (MainActivity) getActivity();
        mCustomDate = CustomDate.getInstance();
    }


}
