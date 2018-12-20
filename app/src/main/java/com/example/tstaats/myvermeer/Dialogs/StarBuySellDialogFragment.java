package com.example.tstaats.myvermeer.Dialogs;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tstaats.myvermeer.Common.Aktien;
import com.example.tstaats.myvermeer.MainActivity;
import com.example.tstaats.myvermeer.Common.PlayerStats;
import com.example.tstaats.myvermeer.R;

public class StarBuySellDialogFragment extends DialogFragment {

    private static final String TAG = "StarBuySellDialogFragm";

    public interface OnStarSelected{
        void changeStarCount(int newStarStock);
    }
    public OnStarSelected mOnStarSelected;

    private MainActivity mainActivity;
    private PlayerStats mPlayer;
    private Aktien mAktien;

    // widgets
    private ImageView iv;
    private TextView aktieNameTv;
    private EditText aktieCountEt;
    private TextView buyAktieTv;
    private TextView sellAktieTv;
    private TextView cancelTv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy_sell_stock_dialog, container, false);

        initBuySell(view);

        aktieNameTv.setText(mAktien.STAR);

        buyAktieTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: buy");

                String input = aktieCountEt.getText().toString();
                if(!input.equals("")){

                    int newStarStock;
                    int count = Integer.parseInt(input);
                    double price = count * mAktien.getStar();

                    if (mPlayer.getPlayerMoney() >= price){
                        double wealth = mPlayer.getPlayerMoney();
                        int currentStarStack = mPlayer.getPlayerStar();

                        mPlayer.setPlayerMoney(wealth - price);
                        mPlayer.setPlayerStar(currentStarStack + count);


                        newStarStock = mPlayer.getPlayerStar();
                        mOnStarSelected.changeStarCount(newStarStock);
                        Toast.makeText(mainActivity, count + " Star für \n" + price + "€ gekauft", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(mainActivity, "Nicht genug geld", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(mainActivity, "Textfelder ausfüllen", Toast.LENGTH_SHORT).show();
                }

                getDialog().dismiss();
            }
        });

        sellAktieTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: sell");
                String input = aktieCountEt.getText().toString();
                if(!input.equals("")){

                    int starStockNew;
                    int count = Integer.parseInt(input);
                    double price = count * mAktien.getStar();

                    if (mPlayer.getPlayerStar() >= count){
                        double wealth = mPlayer.getPlayerMoney();
                        int currentStarStack = mPlayer.getPlayerStar();

                        mPlayer.setPlayerMoney(wealth + price);
                        mPlayer.setPlayerStar(currentStarStack - count);


                        starStockNew = mPlayer.getPlayerStar();
                        mOnStarSelected.changeStarCount(starStockNew);
                        Toast.makeText(mainActivity, count + " Star für \n" + price + "€ verkauft", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(mainActivity, "Nicht genug Aktien", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(mainActivity, "Textfelder ausfüllen", Toast.LENGTH_SHORT).show();
                }

                getDialog().dismiss();
            }
        });

        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: cancel");
                getDialog().dismiss();
            }
        });

        return view;
    }

    private void initBuySell(View view) {

        iv = view.findViewById(R.id.image_wertpapier);
        aktieNameTv = view.findViewById(R.id.text_aktien_name);
        aktieCountEt = view.findViewById(R.id.edittext_aktien_count);
        buyAktieTv = view.findViewById(R.id.text_buy_aktie);
        sellAktieTv = view.findViewById(R.id.text_sell_aktie);
        cancelTv = view.findViewById(R.id.text_cancel_aktie);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mOnStarSelected = (OnStarSelected) getTargetFragment();
        } catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity) getActivity();
        mPlayer = PlayerStats.getInstance();
        mAktien = Aktien.getInstance();
    }
}
