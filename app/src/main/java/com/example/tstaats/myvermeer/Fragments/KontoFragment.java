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
import com.example.tstaats.myvermeer.Dialogs.PayOutDialogFragment;
import com.example.tstaats.myvermeer.Dialogs.PayinDialogFragment;
import com.example.tstaats.myvermeer.MainActivity;
import com.example.tstaats.myvermeer.Common.PlayerStats;
import com.example.tstaats.myvermeer.R;

public class KontoFragment extends Fragment implements PayinDialogFragment.OnInputSelected,
        PayOutDialogFragment.OnInputSelected{

    private static final String TAG = "KontoFragment";
    public static final int ZINSEN = 1;

    @Override
    public void sendInput(double wealthNew, double creditNew) {
        Log.d(TAG, "sendInput: found incoming input: " + wealthNew);
/*        String wealthString = "Vermögen: " + wealthNew + " €";
        String creditString = "Guthaben: " + creditNew + " €";*/
        String wealthString = "Vermögen: " + mPlayer.playerMoneyFormattedToString();
        String creditString = "Guthaben: " + mPlayer.playerCreditFormattedToString();
        wealthTv.setText(wealthString);
        creditTv.setText(creditString);
    }

    private MainActivity mainActivity;
    private PlayerStats mPlayer;
    private Aktien mAktien;
    
    // widgets
    private Button payInBtn;
    private Button payOutBtn;
    public TextView wealthTv;
    public TextView creditTv;

    private TextView paytaxTv;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_konto, container, false);

        initKonto(view);

        payInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: payInBtn clicked");
                PayinDialogFragment payinDialogFragment = new PayinDialogFragment();
                payinDialogFragment.setTargetFragment(KontoFragment.this, 1);
                payinDialogFragment.show(getFragmentManager(), "PayinDialogFragment");

            }
        });

        payOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: payOutBtn clicked");
                PayOutDialogFragment payoutDialogFragment = new PayOutDialogFragment();
                payoutDialogFragment.setTargetFragment(KontoFragment.this, 2);
                payoutDialogFragment.show(getFragmentManager(), "PayOutDialogFragment");
            }
        });
        
        return view;

    }




    private void initKonto(View view) {
        
        payInBtn = view.findViewById(R.id.button_payin);
        payOutBtn = view.findViewById(R.id.button_payout);
        wealthTv = view.findViewById(R.id.text_wealth);
        creditTv = view.findViewById(R.id.text_credit);
        paytaxTv = view.findViewById(R.id.text_paytax);

        String wealthString = "Vermögen: " + mPlayer.playerMoneyFormattedToString();
        String creditString = "Guthaben: " + mPlayer.playerCreditFormattedToString();
        String payTaxString = "Zinsen: " + ZINSEN + "%";
        wealthTv.setText(wealthString);
        creditTv.setText(creditString);
        paytaxTv.setText(payTaxString);
        
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity)getActivity();
        mPlayer = PlayerStats.getInstance();
        mAktien = Aktien.getInstance();
    }


}
