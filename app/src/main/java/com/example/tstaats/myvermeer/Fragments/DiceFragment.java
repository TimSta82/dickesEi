package com.example.tstaats.myvermeer.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tstaats.myvermeer.Common.DicePoint;
import com.example.tstaats.myvermeer.Common.L;
import com.example.tstaats.myvermeer.MainActivity;
import com.example.tstaats.myvermeer.Common.PlayerStats;
import com.example.tstaats.myvermeer.R;

import java.util.ArrayList;
import java.util.Random;

public class DiceFragment extends Fragment {

    private static final String TAG = "DiceFragment";
    public static final int SMALLBET = 500;
    public static final int MIDBET = 1000;
    public static final int BIGBET = 5000;

    private MainActivity mainActivity;
    private PlayerStats mPlayer;

    // widgets
    private TextView welcomeTv;
    private TextView playerMoneyTv;
    private TextView introTv;
    private ImageView diceIv1;
    private ImageView diceIv2;
    private EditText customBetEt;
    private Button smallBtn;
    private Button midBtn;
    private Button bigBtn;
    private Button throwBtn;
    private TextView betSizeTv;
    private TextView pointIsSetTv;
    private TextView p10;
    private TextView p9;
    private TextView p8;
    private TextView p6;
    private TextView p5;
    private TextView p4;

    // vars
    private Random randDice1 = new Random();
    private Random randDice2 = new Random();
    private ArrayList<Integer> mDiceImgList;
    private String welcome = "";
    private String duhast = "";
    private int mResult;
    private int mBet;
    private int mPoint = 0;
    private DicePoint dicePoint;
    private boolean isPointSet = false;
    private int mSmallBet;
    private int mMidBet;
    private int mBigBet;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dice, container, false);

        initDice(view);
        Log.d(TAG, "onCreateView: point: " + mPoint);
        welcome = "Hallo " + mPlayer.getPlayerName() + " Willkommen in der Würfelbude";
        duhast = "Du hast " + mPlayer.getPlayerMoney() + "€ dabei.";
        welcomeTv.setText(welcome);
        playerMoneyTv.setText(duhast);
        throwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input = customBetEt.getText().toString();
                if (!input.equals("")){
                    mBet = customBet();
                    if (mBet > mPlayer.getPlayerMoney()){
                        Toast.makeText(mainActivity, "Nicht genug Geld!", Toast.LENGTH_SHORT).show();
                    } else {
                        mResult = throwDices();
                        betSizeTv.setTextColor(getResources().getColor(R.color.colorGold));
                        betSizeTv.setText(getResources().getString(R.string.dice_bet_size) + mBet + "€");
                        Log.d(TAG, "onClick: mResult: " + mResult + " mPoint: " + dicePoint.getPoint());
                        winOrLose(mBet, mResult, dicePoint.getPoint());
                    }
                    //Toast.makeText(mainActivity, "Ergebnis: " + mResult, Toast.LENGTH_SHORT).show();
                } else {
                    L.t(mainActivity, "Leeres edittextfeld");
                }
            }
        });

        smallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SMALLBET > mPlayer.getPlayerMoney()){
                    Toast.makeText(mainActivity, "Nicht genug Geld!", Toast.LENGTH_SHORT).show();
                } else {
                    mResult = throwDices();
                    betSizeTv.setTextColor(getResources().getColor(R.color.colorGold));
                    betSizeTv.setText(getResources().getString(R.string.dice_bet_size) + SMALLBET + "€");
                    winOrLose(SMALLBET, mResult, mPoint);
                }
            }
        });

        midBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MIDBET > mPlayer.getPlayerMoney()){
                    Toast.makeText(mainActivity, "Nicht genug Geld!", Toast.LENGTH_SHORT).show();
                } else {
                    mResult = throwDices();
                    betSizeTv.setTextColor(getResources().getColor(R.color.colorGold));
                    betSizeTv.setText(getResources().getString(R.string.dice_bet_size) + MIDBET + "€");
                    winOrLose(MIDBET, mResult, mPoint);
                }
            }
        });

        bigBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BIGBET > mPlayer.getPlayerMoney()){
                    Toast.makeText(mainActivity, "Nicht genug Geld!", Toast.LENGTH_SHORT).show();
                } else {
                    mResult = throwDices();
                    betSizeTv.setTextColor(getResources().getColor(R.color.colorGold));
                    betSizeTv.setText(getResources().getString(R.string.dice_bet_size) + BIGBET + "€");
                    winOrLose(BIGBET, mResult, mPoint);
                }
            }
        });

        return view;
    }

    private void winOrLose(int mBet, int mResult, int mPoint) {
        double win;
        double currentMoney = mPlayer.getPlayerMoney();
        switch (mResult){
            // instant lose game
            case 2:
                if (isPointSet == true) {
                    Log.d(TAG, "winOrLose: isPointSet: " + isPointSet + " pointValue: " + dicePoint.getPoint());
                } else {
                    L.t(mainActivity, "winOrLose: Craps! Verloren...");
                    win = mBet;
                    isPointSet = false;
                    mPlayer.setPlayerMoney(currentMoney - win);
                    clearBetTextView();
                    refreshTextView();
                    dicePoint.setPoint(0);
                    setAllPointTextViewsDefault();
                }
                break;
            case 3:
                if (isPointSet == true) {
                    Log.d(TAG, "winOrLose: isPointSet: " + isPointSet + " pointValue: " + dicePoint.getPoint());
                } else {
                    L.t(mainActivity, "winOrLose: Craps! Verloren...");
                    win = mBet;
                    isPointSet = false;
                    mPlayer.setPlayerMoney(currentMoney - win);
                    clearBetTextView();
                    refreshTextView();
                    dicePoint.setPoint(0);
                    setAllPointTextViewsDefault();
                }
                break;
            case 12:
                if (isPointSet == true) {
                    Log.d(TAG, "winOrLose: isPointSet: " + isPointSet + " pointValue: " + dicePoint.getPoint());
                } else {
                    L.t(mainActivity, "winOrLose: Craps! Verloren...");
                    win = mBet;
                    isPointSet = false;
                    mPlayer.setPlayerMoney(currentMoney - win);
                    clearBetTextView();
                    refreshTextView();
                    dicePoint.setPoint(0);
                    setAllPointTextViewsDefault();
                }
                break;
            // instant win game
            case 11:
                if (isPointSet == true){
                    Log.d(TAG, "winOrLose: isPointSet: " + isPointSet + " pointValue: " + dicePoint.getPoint());
                } else {
                    L.t(mainActivity, "winOrLose: Gewonnen");
                    win = mBet;
                    mPlayer.setPlayerMoney(currentMoney + win);
                    clearBetTextView();
                    refreshTextView();
                    dicePoint.setPoint(0);
                    setAllPointTextViewsDefault();
                }
                break;
            case 7:
                if (isPointSet == false){
                    L.t(mainActivity, "winOrLose: Gewonnen");
                    win = mBet;
                    isPointSet = false;
                    mPlayer.setPlayerMoney(currentMoney + win);
                    clearBetTextView();
                    refreshTextView();
                    setAllPointTextViewsDefault();
                } else {
                    L.t(mainActivity, "winOrLose: Craps! Verloren...");
                    win = mBet;
                    isPointSet = false;
                    mPlayer.setPlayerMoney(currentMoney - win);
                    clearBetTextView();
                    refreshTextView();
                    Log.d(TAG, "winOrLose: verloren da 7 gewürfelt bevor point getroffen.");
                    //mPoint = 0;
                    dicePoint.setPoint(0);
                    setAllPointTextViewsDefault();
                    Log.d(TAG, "winOrLose: point war gesetzt: point ist wieder auf: " + dicePoint.getPoint());
                }
                break;
            // set point
            case 4:
                if (isPointSet == true && dicePoint.getPoint() == mResult){
                    Log.d(TAG, "winOrLose: mPoint: " + dicePoint.getPoint() + " mResult: " + mResult + " gewonnen!");
                    win = mBet;
                    isPointSet = false;
                    mPlayer.setPlayerMoney(currentMoney + win);
                    clearBetTextView();
                    refreshTextView();
                    setAllPointTextViewsDefault();
                } else if (isPointSet == false){
                    //mPoint = 4;
                    dicePoint.setPoint(4);
                    isPointSet = true;
                    setAllPointTextViewsDefault();
                    p4.setBackgroundColor(getResources().getColor(R.color.colorGold));
                    p4.setTextColor(getResources().getColor(R.color.colorFilzGreen));
                    pointIsSet();
                    Log.d(TAG, "winOrLose: mPoint: " + dicePoint.getPoint() + " mResult: " + mResult + " point gesetzt!");
                }
                break;
            case 5:
                if (isPointSet == true && dicePoint.getPoint() == mResult){
                    Log.d(TAG, "winOrLose: mPoint: " + dicePoint.getPoint() + " mResult: " + mResult + " gewonnen!");
                    win = mBet;
                    isPointSet = false;
                    mPlayer.setPlayerMoney(currentMoney + win);
                    clearBetTextView();
                    refreshTextView();
                    setAllPointTextViewsDefault();
                } else if (isPointSet == false) {
                    //mPoint = 5;
                    dicePoint.setPoint(5);
                    isPointSet = true;
                    setAllPointTextViewsDefault();
                    p5.setBackgroundColor(getResources().getColor(R.color.colorGold));
                    p5.setTextColor(getResources().getColor(R.color.colorFilzGreen));
                    pointIsSet();
                    Log.d(TAG, "winOrLose: mPoint: " + dicePoint.getPoint() + " mResult: " + mResult + " point gesetzt!");
                }
                break;
            case 6:
                if (isPointSet == true && dicePoint.getPoint() == mResult){
                    Log.d(TAG, "winOrLose: mPoint: " + dicePoint.getPoint() + " mResult: " + mResult + " gewonnen!");
                    win = mBet;
                    isPointSet = false;
                    mPlayer.setPlayerMoney(currentMoney + win);
                    clearBetTextView();
                    refreshTextView();
                    setAllPointTextViewsDefault();
                } else if (isPointSet == false) {
                    //mPoint = 6;
                    dicePoint.setPoint(6);
                    isPointSet = true;
                    setAllPointTextViewsDefault();
                    p6.setBackgroundColor(getResources().getColor(R.color.colorGold));
                    p6.setTextColor(getResources().getColor(R.color.colorFilzGreen));
                    pointIsSet();
                    Log.d(TAG, "winOrLose: mPoint: " + dicePoint.getPoint() + " mResult: " + mResult + " point gesetzt!");
                }
                break;
            case 8:
                if (isPointSet == true && dicePoint.getPoint() == mResult){
                    Log.d(TAG, "winOrLose: mPoint: " + dicePoint.getPoint() + " mResult: " + mResult + " gewonnen!");
                    win = mBet;
                    isPointSet = false;
                    mPlayer.setPlayerMoney(currentMoney + win);
                    clearBetTextView();
                    refreshTextView();
                    setAllPointTextViewsDefault();
                } else if (isPointSet == false) {
                    //mPoint = 8;
                    dicePoint.setPoint(8);
                    isPointSet = true;
                    setAllPointTextViewsDefault();
                    p8.setBackgroundColor(getResources().getColor(R.color.colorGold));
                    p8.setTextColor(getResources().getColor(R.color.colorFilzGreen));
                    pointIsSet();
                    Log.d(TAG, "winOrLose: mPoint: " + dicePoint.getPoint() + " mResult: " + mResult + " point gesetzt!");
                }
                break;
            case 9:
                if (isPointSet == true && dicePoint.getPoint() == mResult){
                    Log.d(TAG, "winOrLose: mPoint: " + dicePoint.getPoint() + " mResult: " + mResult + " gewonnen!");
                    win = mBet;
                    isPointSet = false;
                    mPlayer.setPlayerMoney(currentMoney + win);
                    clearBetTextView();
                    refreshTextView();
                    setAllPointTextViewsDefault();
                } else if (isPointSet == false) {
                    //mPoint = 9;
                    dicePoint.setPoint(9);
                    isPointSet = true;
                    setAllPointTextViewsDefault();
                    p9.setBackgroundColor(getResources().getColor(R.color.colorGold));
                    p9.setTextColor(getResources().getColor(R.color.colorFilzGreen));
                    pointIsSet();
                    Log.d(TAG, "winOrLose: mPoint: " + dicePoint.getPoint() + " mResult: " + mResult + " point gesetzt!");
                }
                break;
            case 10:
                if (isPointSet == true && dicePoint.getPoint() == mResult){
                    Log.d(TAG, "winOrLose: mPoint: " + dicePoint.getPoint() + " mResult: " + mResult + " gewonnen!");
                    win = mBet;
                    isPointSet = false;
                    mPlayer.setPlayerMoney(currentMoney + win);
                    clearBetTextView();
                    refreshTextView();
                    setAllPointTextViewsDefault();

                } else if (isPointSet == false) {
                    dicePoint.setPoint(10);
                    isPointSet = true;
                    setAllPointTextViewsDefault();
                    p10.setBackgroundColor(getResources().getColor(R.color.colorGold));
                    p10.setTextColor(getResources().getColor(R.color.colorFilzGreen));
                    pointIsSet();
                    Log.d(TAG, "winOrLose: mPoint: " + dicePoint.getPoint() + " mResult: " + mResult + " point gesetzt!");
                }
                break;
            default:
                L.t(mainActivity, "winOrLose: verloren");
                win = mBet;
                isPointSet = false;
                mPlayer.setPlayerMoney(currentMoney - win);
                refreshTextView();
                break;
        }
    }

    private void clearBetTextView(){
        betSizeTv.setTextColor(getResources().getColor(R.color.colorFilzGreen));
    }

    private void pointIsSet(){
        pointIsSetTv.setTextColor(getResources().getColor(R.color.colorGold));
    }

    private void setAllPointTextViewsDefault(){
        p10.setBackgroundColor(getResources().getColor(R.color.colorFilzGreen));
        p10.setTextColor(getResources().getColor(R.color.colorGold));
        p9.setBackgroundColor(getResources().getColor(R.color.colorFilzGreen));
        p9.setTextColor(getResources().getColor(R.color.colorGold));
        p8.setBackgroundColor(getResources().getColor(R.color.colorFilzGreen));
        p8.setTextColor(getResources().getColor(R.color.colorGold));
        p6.setBackgroundColor(getResources().getColor(R.color.colorFilzGreen));
        p6.setTextColor(getResources().getColor(R.color.colorGold));
        p5.setBackgroundColor(getResources().getColor(R.color.colorFilzGreen));
        p5.setTextColor(getResources().getColor(R.color.colorGold));
        p4.setBackgroundColor(getResources().getColor(R.color.colorFilzGreen));
        p4.setTextColor(getResources().getColor(R.color.colorGold));
        pointIsSetTv.setTextColor(getResources().getColor(R.color.colorFilzGreen));

    }




    private void refreshTextView(){
        welcomeTv.setText(welcome);
        playerMoneyTv.setVisibility(View.INVISIBLE);
        playerMoneyTv.setText("Du hast " + mPlayer.getPlayerMoney() + "€ dabei.");
        playerMoneyTv.setVisibility(View.VISIBLE);
    }

    private void initDice(View view) {

        welcomeTv = view.findViewById(R.id.text_casino_welcome);
        playerMoneyTv = view.findViewById(R.id.text_player_money);
        introTv = view.findViewById(R.id.text_bet);
        diceIv1 = view.findViewById(R.id.image_dice1);
        diceIv2 = view.findViewById(R.id.image_dice2);
        customBetEt = view.findViewById(R.id.editText_custombet);
        smallBtn = view.findViewById(R.id.button_small_bet);
        midBtn = view.findViewById(R.id.button_mid_bet);
        bigBtn = view.findViewById(R.id.button_big_bet);
        throwBtn = view.findViewById(R.id.button_throw_dice);

        betSizeTv = view.findViewById(R.id.text_betsize);
        pointIsSetTv = view.findViewById(R.id.text_is_point_set);
        //betSizeTv.setTextColor(getResources().getColor(R.color.colorFilzGreen));
        pointIsSetTv.setTextColor(getResources().getColor(R.color.colorFilzGreen));

        p10 = view.findViewById(R.id.text_10);
        p9 = view.findViewById(R.id.text_9);
        p8 = view.findViewById(R.id.text_8);
        p6 = view.findViewById(R.id.text_6);
        p5 = view.findViewById(R.id.text_5);
        p4 = view.findViewById(R.id.text_4);



        dicePoint = new DicePoint(0);

        mDiceImgList = new ArrayList<>();
        mDiceImgList.add(R.drawable.dice1);
        mDiceImgList.add(R.drawable.dice2);
        mDiceImgList.add(R.drawable.dice3);
        mDiceImgList.add(R.drawable.dice4);
        mDiceImgList.add(R.drawable.dice5);
        mDiceImgList.add(R.drawable.dice6);

    }

    private int customBet(){
        Log.d(TAG, "customBet: pre bet playerMoney: " + mPlayer.getPlayerMoney());
        int bet = Integer.valueOf(customBetEt.getText().toString());
        if (bet > mPlayer.getPlayerMoney()) {
            L.t(mainActivity, "Nicht genug Kohle");
            bet = 0;
        }
        return bet;
    }

    private int throwDices(){

        int d1 = randDice1.nextInt(6);
        int d2 = randDice2.nextInt(6);
        int result = d1 + d2 + 2;

        diceIv1.setImageResource(mDiceImgList.get(d1));
        diceIv2.setImageResource(mDiceImgList.get(d2));

        return result;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity) getActivity();

        mPlayer = PlayerStats.getInstance();
    }
}
