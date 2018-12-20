package com.example.tstaats.myvermeer.Fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tstaats.myvermeer.Common.Kunstwerk;
import com.example.tstaats.myvermeer.Common.PlayerStats;
import com.example.tstaats.myvermeer.MainActivity;
import com.example.tstaats.myvermeer.R;

import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class AuktionFragment extends Fragment {

    private static final String TAG = "AuktionFragment";

    public static final long START_AUKTION_TIME = 6000;
    public static final long AUKTION_INTERVAL_TIME = 1500;

    private MainActivity mainActivity;
    private PlayerStats mPlayer;
    private Kunstwerk imageForSale;
    private Button backBtn;
    private Button betBtn;
    private TextView countTv;
    private TextView startPriceTv;
    private TextView currentPriceTv;

    // opponent bet
    private boolean isKnauerBetting = false;
    private boolean isFrankBetting = false;
    private CircleImageView opponent1Image;
    //private ImageView opponent2Image;
    private CircleImageView opponent2Image;
    private TextView oponent1Name;
    private TextView oponent2Name;
    private TextView oponent1text;
    private TextView oponent2text;

    private ImageView auktionIv;
    private boolean isAuktionStarted = false;

    private int startPrice;
    private int currentPrice;

    private CountDownTimer mCountDownTimer;
    private long mTimeLeftinMillis = START_AUKTION_TIME;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_auktion, container, false);

        initAuktion(view);
        Log.d(TAG, "onCreateView: startprice: " + startPrice);
        Log.d(TAG, "onCreateView: currentprice: " + currentPrice);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.fragmentSwitcher(new HomeFragment(), "HomeFragment");
            }
        });

        betBtn.setText("Starte Auktion!");
        betBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isAuktionStarted){
                    resetAuktionTimer();
                    bet();
                    playerBet();
                    opponent1Image.setAlpha(0.2f);
                    oponent1text.setVisibility(View.INVISIBLE);
                    opponent2Image.setAlpha(0.2f);
                    oponent2text.setVisibility(View.INVISIBLE);
                    isFrankBetting = false;
                    isKnauerBetting = false;
                } else {
                    startAuktionTimer();
                }
            }
        });

        //updateText("Neustart...");

        return view;

    }

    private void playerBet() {
        opponent1Image.setAlpha(0.2f);
        oponent1text.setVisibility(View.INVISIBLE);
        opponent2Image.setAlpha(0.2f);
        oponent2text.setVisibility(View.INVISIBLE);
        isFrankBetting = false;
        isKnauerBetting = false;
    }

    private void initAuktion(View view) {
        opponent1Image = view.findViewById(R.id.image_opponent1);
        opponent1Image.setImageResource(R.drawable.knauer);
        opponent2Image = view.findViewById(R.id.image_opponent2);
        opponent2Image.setImageResource(R.drawable.knauer2);
        oponent1Name = view.findViewById(R.id.text_opponent1);
        oponent2Name = view.findViewById(R.id.text_opponent2);
        oponent1text = view.findViewById(R.id.text_opponent1_text);
        oponent2text = view.findViewById(R.id.text_opponent2_text);

        backBtn = view.findViewById(R.id.button_back_auktion);
        betBtn = view.findViewById(R.id.button_bet_auktion);
        countTv = view.findViewById(R.id.text_countdown);
        startPriceTv = view.findViewById(R.id.text_startprice);
        currentPriceTv = view.findViewById(R.id.text_currentprice);
        auktionIv = view.findViewById(R.id.image_auktion);

        imageForSale = mainActivity.prepareImageForAuktion();
        auktionIv.setImageResource(imageForSale.getImageId());
        startPrice = imageForSale.getImageValue();
        currentPrice = startPrice;

        startPriceTv.setText("Startpreis:\n" + startPrice + "€");
        currentPriceTv.setText("");

    }

    private void startAuktionTimer() {

        mCountDownTimer = new CountDownTimer(mTimeLeftinMillis, AUKTION_INTERVAL_TIME) {
            int i = 3;

            @Override
            public void onTick(long millisUntilFinished) {

                mTimeLeftinMillis = millisUntilFinished;
                i--;
                switch (i){
                    case 2:
                        updateText("Zum ersten ... " );
                        enemyBet();
                        break;
                    case 1:
                        updateText("... und zum zweiten ..." );
                        enemyBet();
                        break;
                    case 0:
                        updateText("... und ...");
                        enemyBet();
                        break;
                }
            }

            @Override
            public void onFinish() {
                backBtn.setEnabled(true);
                betBtn.setEnabled(false);
                countTv.setAlpha(1.0f);
                countTv.setText("..... Verkauft!");
                imageForSale.setImageSoldFor(currentPrice);
                mainActivity.auktionFinishedImageSold(imageForSale);
                isAuktionStarted = false;
                if (isFrankBetting == false && isKnauerBetting == false){
                    double cur = mPlayer.getPlayerMoney();
                    mPlayer.setPlayerMoney(cur - currentPrice);
                    mainActivity.addImageToPlayer(imageForSale);
                } else {
                    Log.d(TAG, "onFinish: bild von knauer oder frank gekauft");
                }
            }

        }.start();

        isAuktionStarted = true;
        betBtn.setText("Bieten!");
        backBtn.setEnabled(false);

    }

    private int whoIsBetting(){

        Random randOpponent = new Random();
        int opponent = randOpponent.nextInt(2);

        return opponent;
    }

    private void enemyBet() {
        Random randEnemy = new Random();

        int opponent;
        Log.d(TAG, "enemyBet: was called");
        int enemyInt = randEnemy.nextInt(4);
        if(enemyInt < 2){
            opponent = whoIsBetting();

            if (opponent == 0 && isKnauerBetting == false){
                Log.d(TAG, "enemyBet: Knauer");
                opponent1Image.setAlpha(1.0f);
                opponent2Image.setAlpha(0.2f);
                oponent2text.setVisibility(View.INVISIBLE);
                oponent1text.setVisibility(View.VISIBLE);
                int knauerBet = currentPrice + 1000;
                oponent1text.setText("Normal, Ich biete... \n" + knauerBet + " €");
                oponent1text.setAlpha(1.0f);
                isKnauerBetting = true;
                isFrankBetting = false;
                Random r = new Random();
                long t = (long) r.nextInt(9) * 100;
                new CountDownTimer(t, 100){
                    @Override
                    public void onTick(long millisUntilFinished) { }

                    @Override
                    public void onFinish() {
                        bet();
                    }
                }.start();

            } else if (opponent == 1 && isFrankBetting == false){
                Log.d(TAG, "enemyBet: Frank");
                opponent2Image.setAlpha(1.0f);
                opponent1Image.setAlpha(0.2f);
                oponent2text.setVisibility(View.VISIBLE);
                oponent1text.setVisibility(View.INVISIBLE);
                int frankBet = currentPrice + 1000;
                oponent2text.setText("Digger laber nich\nIch biete mähr\n" + frankBet + " €");
                oponent2text.setAlpha(1.0f);
                isKnauerBetting = false;
                isFrankBetting = true;
                Random r = new Random();
                long t = (long) r.nextInt(9) * 100;
                new CountDownTimer(t, 100){
                    @Override
                    public void onTick(long millisUntilFinished) { }

                    @Override
                    public void onFinish() {
                        bet();
                    }
                }.start();
            }
            resetAuktionTimer();
        }

    }

    private void updateText(String text) {
        countTv.setAlpha(1.0f);
        countTv.setText(text);
        countTv.animate().alpha(0.0f).setDuration(2000).start();
    }

    private void resetAuktionTimer() {
        mCountDownTimer.cancel();
        mTimeLeftinMillis = START_AUKTION_TIME;
        startAuktionTimer();
    }

    public void bet(){

        if(!isAuktionStarted){
            currentPrice = startPrice;
        } else {
            int betSize = 1000;
            currentPrice = currentPrice + betSize;
            currentPriceTv.setText(currentPrice + "€\nsind geboten...");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity) getActivity();
        mPlayer = PlayerStats.getInstance();
    }
}
