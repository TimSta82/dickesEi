package com.example.tstaats.myvermeer.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tstaats.myvermeer.Common.Aktien;
import com.example.tstaats.myvermeer.Common.PlayerStats;
import com.example.tstaats.myvermeer.Common.RawMaterials;
import com.example.tstaats.myvermeer.MainActivity;
import com.example.tstaats.myvermeer.R;

public class OverviewFragment extends Fragment {

    private static final String TAG = "OverviewFragment";

    private TextView contentTv;

    private MainActivity mainActivity;
    private PlayerStats mPlayer;
    private Aktien mAktien;
    private RawMaterials mRawMaterials;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_overview, container, false);

        initOverview(view);

        contentTv.setText("Player:\n" + mPlayer.toString() + "\nBargeld: " + mPlayer.playerMoneyFormattedToString() + "\nKonto: " + mPlayer.playerCreditFormattedToString() +"\n\nAktien: \n" +
                mPlayer.stockDetailsToString() + "\n\nAktienkurs: \n" + mAktien.toString() + "\n\nRohstoffe: \n" +
                mPlayer.rawMaterialsToString() + "\nRohstoffpreise: \n" + mRawMaterials.toString()+ "\n\nSammlung: \n" +
                mPlayer.kunstwerkListToString() + "\n" + mPlayer.plantagenListToString() + "\n");

        return view;
    }

    private void initOverview(View view) {
        contentTv = view.findViewById(R.id.text_overview_content);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity)getActivity();

        mPlayer = PlayerStats.getInstance();
        mAktien = Aktien.getInstance();
        mRawMaterials = RawMaterials.getInstance();
    }
}
