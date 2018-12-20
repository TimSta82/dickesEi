package com.example.tstaats.myvermeer;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tstaats.myvermeer.Common.Aktien;
import com.example.tstaats.myvermeer.Common.City;
import com.example.tstaats.myvermeer.Common.CustomDate;
import com.example.tstaats.myvermeer.Common.KunstSammlung;
import com.example.tstaats.myvermeer.Common.Kunstwerk;
import com.example.tstaats.myvermeer.Common.PlayerStats;
import com.example.tstaats.myvermeer.Common.RawMaterials;
import com.example.tstaats.myvermeer.Fragments.BankFragment;
import com.example.tstaats.myvermeer.Fragments.HomeFragment;
import com.example.tstaats.myvermeer.Fragments.TravelFragment;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";
    private static final int PLANT_SIZE = 63;
    public static double RENT = 0.01f;
    public BottomNavigationView mBottomNavigationView;
    private ConstraintLayout mTopLayout;
    public static final double ROYAL = 555d;
    public static final double STAR = 444d;
    public static final double LLOYD = 333d;
    public static final double HANSE = 222d;

    public static final double TABAK = 555d;
    public static final double TEE = 444d;
    public static final double KAFFEE = 333d;
    public static final double KAKAO = 222d;

    // widgets
    private TextView mCityNameTv;
    public TextView mDateTv;
    private Button mBtnSettings;
    //private Toolbar mToolbar;

    // singletons
    private PlayerStats mPlayer;
    private CustomDate mCustomDate;
    private Aktien mAktien;
    private RawMaterials mRawMaterials;
    private KunstSammlung mSammlung;

    private HomeFragment mHomeFragment;
    // vars
    public ArrayList<Kunstwerk> mImageList = new ArrayList<>();
    //public City mCity;
    //public ArrayList<City> mCityList;
    public double mLloyd = 0.0;
    public double mStar = 0.0;
    public double mHanse = 0.0;
    public double mRoyal = 0.0;
    public double mTabak = 0.0;
    public double mTee = 0.0;
    public double mKaffee = 0.0;
    public double mKakao = 0.0;
    public boolean isReady = false;
    public boolean newStockValuesCreated = false;
    public boolean newRawMaterialsValuesCreated = false;
    public boolean newCreditRentCalculated = false;

    //public List<FieldItem> fieldItemList;
    public List<ImageButton> fieldItemList;
    public List<Boolean> fieldItemIsCountedList;

    public enum PlantResource {TABAK, TEE, KAFFEE, KAKAO, NATURE}
    public PlantResource mChoosenResource;

    // for graphview
    public LineGraphSeries<DataPoint> mSeriesLloyd;
    public LineGraphSeries<DataPoint> mSeriesStar;
    public LineGraphSeries<DataPoint> mSeriesHanse;
    public LineGraphSeries<DataPoint> mSeriesRoyal;
    public double graphLastXValue = 5d;


/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.);
    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMainActivity();

        //setSupportActionBar(mToolbar);


        mSeriesLloyd = new LineGraphSeries<>();
        mSeriesStar = new LineGraphSeries<>();
        mSeriesHanse = new LineGraphSeries<>();
        mSeriesRoyal = new LineGraphSeries<>();


        mPlayer = PlayerStats.getInstance();
        mCustomDate = CustomDate.getInstance();
        initDate();
        displayDate(mCustomDate.toString());
        //createCityList();
        initPlayer();
        mAktien = Aktien.getInstance();
        initAktien();
        mRawMaterials = RawMaterials.getInstance();
        initRawMaterials();
        mSammlung = KunstSammlung.getInstance();
        mSammlung.setSammlung();

        createStockValues();
        createRawMaterialsValues();

        //displayCityName(mPlayer.getCurrentLocation().getCityName());

        mBottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        // Inflate Fragment in MainActivity
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, mHomeFragment, "HomeFragment");
        transaction.commit();
    }

    public void generateData(){
        graphLastXValue += 1d;
        mSeriesLloyd.appendData(new DataPoint(graphLastXValue, mAktien.getLloyd()), true, 40); //40
        mSeriesStar.appendData(new DataPoint(graphLastXValue, mAktien.getStar()), true, 40);
        mSeriesHanse.appendData(new DataPoint(graphLastXValue, mAktien.getHanse()), true, 40);
        mSeriesRoyal.appendData(new DataPoint(graphLastXValue, mAktien.getRoyal()), true, 40);
    }

    public PlantResource getChoosenResource() {
        return mChoosenResource;
    }

    public void setChoosenResource(PlantResource choosenResource){
        mChoosenResource = choosenResource;
    }

    public List<ImageButton> createFieldItemList(){
        fieldItemList = new ArrayList<>();
        int fieldImageResource = R.drawable.ic_nature;
        for (int i = 0; i < PLANT_SIZE ; i++) {
            //fieldItemList.add(new FieldItem(new ImageButton(getBaseContext()), fieldImageResource));
            fieldItemList.add(new ImageButton(getBaseContext()));
        }

        return fieldItemList;
    }

    public List<Boolean> createFieldItemIsCountedList(){
        fieldItemIsCountedList = new ArrayList<>();
        Boolean isCounted = false;
        for (int i = 0; i < PLANT_SIZE; i++) {
            fieldItemIsCountedList.add(isCounted);
        }

        return fieldItemIsCountedList;
    }

    private void createRawMaterialsValues() {
        mKaffee = mRawMaterials.getKaffee();
        mTabak = mRawMaterials.getTabak();
        mTee = mRawMaterials.getTee();
        mKakao = mRawMaterials.getKakao();
        createTabakValue();
        createTeeValue();
        createKaffeeValue();
        createKakaoValue();
        newRawMaterialsValuesCreated = true;
    }

    private void createKakaoValue() {
        double value = mRawMaterials.getKakao();
        Random rand = new Random();
        int x = rand.nextInt(2);
        if (x == 1){
            value = value + 10.0;
        } else {
            value = value - 10.0;
        }
        mRawMaterials.setKakao(value);
        if (mRawMaterials.getKakao() < 0){
            mRawMaterials.setKakao(0.0);
        }
    }

    private void createKaffeeValue() {
        double value = mRawMaterials.getKaffee();
        Random rand = new Random();
        int x = rand.nextInt(2);
        if (x == 1){
            value = value + 10.0;
        } else {
            value = value - 10.0;
        }
        mRawMaterials.setKaffee(value);
        if (mRawMaterials.getKaffee() < 0){
            mRawMaterials.setKaffee(0.0);
        }
    }

    private void createTeeValue() {
        double value = mRawMaterials.getTee();
        Random rand = new Random();
        int x = rand.nextInt(2);
        if (x == 1){
            value = value + 10.0;
        } else {
            value = value - 10.0;
        }
        mRawMaterials.setTee(value);
        if (mRawMaterials.getTee() < 0){
            mRawMaterials.setTee(0.0);
        }
    }

    private void createTabakValue() {
        double value = mRawMaterials.getTabak();
        Random rand = new Random();
        int x = rand.nextInt(10);
        if (x == 1){
            value = value + 10.0;
        } else if (x == 2) {
            value = value + 30.0;
        } else if (x == 3) {
            value = value - 30.0;
        } else if (x == 4) {
            value = value + 5.0;
        } else {
            value = value - 10.0;
        }
        mRawMaterials.setTabak(value);
        if (mRawMaterials.getTabak() < 0){
            mRawMaterials.setTabak(1.0);
        }
    }

    private void initRawMaterials() {
        mRawMaterials.setKaffee(KAFFEE);
        mRawMaterials.setKakao(KAKAO);
        mRawMaterials.setTabak(TABAK);
        mRawMaterials.setTee(TEE);
    }


    private void initMainActivity(){

        //mToolbar = findViewById(R.id.toolbar);
        mTopLayout = findViewById(R.id.top_layout);
        mBottomNavigationView = findViewById(R.id.bottom_nav);
        mCityNameTv = findViewById(R.id.text_city_name);
        mDateTv = findViewById(R.id.text_date);
        mBtnSettings = findViewById(R.id.button_settings);

        mBtnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: mBtnSettings pressed");
            }
        });

        mHomeFragment = new HomeFragment();

    }

    private void initPlayer(){
        mPlayer.setPlayerName("Player 1");

//        mPlayer.setCurrentLocation(new City(mCityList.get(0).getCurrentTownInfo(),mCityList.get(0).getCityName(), mCityList.get(0).getCityImg(), mCityList.get(0).getCountryName(),
//                mCityList.get(0).getCountryFlag(), mCityList.get(0).getPoliticalStatus(),
//                mCityList.get(0).getLatitude(), mCityList.get(0).getLongitude()));
        mPlayer.setPlayerMoney(1000000);
        mPlayer.setPlayerCredit(50);
        mPlayer.setPlayerLloyd(5);
        mPlayer.setPlayerStar(5);
        mPlayer.setPlayerHanse(5);
        mPlayer.setPlayerRoyal(5);
        mPlayer.setPlayerTabak(0);
        mPlayer.setPlayerTee(0);
        mPlayer.setPlayerKaffee(0);
        mPlayer.setPlayerKakao(0);
    }

    private void initAktien(){
        mAktien.setHanse(HANSE);
        mAktien.setLloyd(LLOYD);
        mAktien.setStar(STAR);
        mAktien.setRoyal(ROYAL);
    }

    private void initDate(){
        mCustomDate.setYear(1982);
        mCustomDate.setMonth(12);
        mCustomDate.setDay(25);
    }

/*
    public void createCityList(){
        mCityList = new ArrayList<>();
        mCity = new City();
        mCity.setListOfCities();
        mCityList = mCity.getListOfCities();

    }
*/

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment selectedFragment = null;
            String fragmentName = "";
            switch (menuItem.getItemId()){

                case R.id.nav_home:
                    Log.d(TAG, "onNavigationItemSelected: HomeFragment");
                    selectedFragment = mHomeFragment;
                    fragmentName = "HomeFragment";
                    break;
                case R.id.nav_bank:
                    Log.d(TAG, "onNavigationItemSelected: BankFragment");
                    selectedFragment = new BankFragment();
                    fragmentName = "BankFragment";
                    break;
                case R.id.nav_travel:
                    Log.d(TAG, "onNavigationItemSelected: TravelFragment");
                    selectedFragment = new TravelFragment();
                    fragmentName = "TravelFragment";
                    break;

            }

            fragmentSwitcher(selectedFragment, fragmentName);

            return true;
        }
    };


    public void fragmentSwitcher(Fragment selectedFragment, String fragmentName){

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, selectedFragment, fragmentName);
        transaction.commit();
    }

    public boolean playerHasMoney(){

        if (mPlayer.getPlayerMoney() < -1000){
            Log.d(TAG, "playerHasMoney: hat kein geld");
            return false;
        } else {
            Log.d(TAG, "playerHasMoney: hat kohle");
            return true;
        }

    }

    public void displayCityName(String cityName){
        mCityNameTv.setText(cityName);
    }

    public void displayDate(String date){
        mDateTv.setText(date);
    }

    public void calculatePayRent(){
        double currPlayerCredit = mPlayer.getPlayerCredit();
        currPlayerCredit = currPlayerCredit + currPlayerCredit * RENT;
        mPlayer.setPlayerCredit(currPlayerCredit);
        newCreditRentCalculated = true;
    }

    public void createStockValues(){
        mLloyd = mAktien.getLloyd();
        mStar = mAktien.getStar();
        mHanse = mAktien.getHanse();
        mRoyal = mAktien.getRoyal();
        createLloydValue();
        createStarValue();
        createHanseValue();
        createRoyalValue();
        newStockValuesCreated = true;
    }

    private void createRoyalValue() {
        double value = mAktien.getRoyal();
        Random rand = new Random();
        int x = rand.nextInt(10);
        if (x == 1){
            value = value + 10.0;
        } else if (x == 2) {
            value = value - 10.0;
        } else if (x == 3) {
            value = value + 30.0;
        } else if (x == 4) {
            value = value + 30.0;
        } else if (x > 4 && x < 8) {
            value = value + 5.0;
        } else if (x > 8) {
            value = value - 5.0;
        } else {
            value = value - 1.0;
        }
        mAktien.setRoyal(value);
        if (mAktien.getRoyal() < 0){
            mAktien.setRoyal(1.0);
        }
    }

    private void createHanseValue() {
        double value = mAktien.getHanse();
        Random rand = new Random();
        int x = rand.nextInt(10);
        if (x == 1){
            value = value + 10.0;
        } else if (x == 2) {
            value = value - 10.0;
        } else if (x == 3) {
            value = value + 30.0;
        } else if (x == 4) {
            value = value + 30.0;
        } else if (x > 4 && x < 8) {
            value = value + 5.0;
        } else if (x > 8) {
            value = value - 5.0;
        } else {
            value = value - 1.0;
        }
        mAktien.setHanse(value);
        if (mAktien.getHanse() < 0){
            mAktien.setHanse(1.0);
        }
    }

    private void createStarValue() {
        double value = mAktien.getStar();
        Random rand = new Random();
        int x = rand.nextInt(10);
        if (x == 1){
            value = value + 10.0;
        } else if (x == 2) {
            value = value - 10.0;
        } else if (x == 3) {
            value = value + 30.0;
        } else if (x == 4) {
            value = value + 30.0;
        } else if (x > 4 && x < 8) {
            value = value + 5.0;
        } else if (x > 8) {
            value = value - 5.0;
        } else {
            value = value - 1.0;
        }
        mAktien.setStar(value);
        if (mAktien.getStar() < 0){
            mAktien.setStar(1.0);
        }
    }

    private void createLloydValue() {
        double value = mAktien.getLloyd();
        Random rand = new Random();
        int x = rand.nextInt(10);
        if (x == 1){
            value = value + 10.0;
        } else if (x == 2) {
            value = value - 10.0;
        } else if (x == 3) {
            value = value + 30.0;
        } else if (x == 4) {
            value = value + 30.0;
        } else if (x > 4 && x < 8) {
            value = value + 5.0;
        } else if (x > 8) {
            value = value - 5.0;
        } else {
            value = value - 1.0;
        }
        mAktien.setLloyd(value);
        if (mAktien.getLloyd() < 0){
            mAktien.setLloyd(1.0);
        }
    }

    public void calendar(int timeSpan){

        long dauer = (long) timeSpan;
        dauer = dauer * 100;

        new CountDownTimer(dauer, 100){

            @Override
            public void onTick(long millisUntilFinished) {

                int day = mCustomDate.getDay();
                day = day + 1;
                mCustomDate.setDay(day);
                Log.d(TAG, "onTick: customDate.toString(): " + mCustomDate.toString() + " isMonthSwitch(): " + mCustomDate.isMonthSwitch());
                if (mCustomDate.isMonthSwitch()){
                    Log.d(TAG, "onTick: monthSwitch is true: createStockValues() is called");

                    //mOnOldStockChanged.sendOldStock(mAktien.toString());
                    generateData();
                    createStockValues();
                    newStockValuesCreated = false;
                    createRawMaterialsValues();
                    newRawMaterialsValuesCreated = false;
                    calculatePayRent();
                    newCreditRentCalculated = false;
                    //mOnStockChanged.sendStock();
                }
                displayDate(mCustomDate.toString());
            }

            @Override
            public void onFinish() {
                int day = mCustomDate.getDay();
                day = day + 1;
                mCustomDate.setDay(day);
                displayDate(mCustomDate.toString());

            }

        }.start();
    }

    public Kunstwerk prepareImageForAuktion(){

        Random randImage = new Random();
        int x = randImage.nextInt(mSammlung.getSammlung().size());
//        ArrayList<Kunstwerk> auktionsSammlung = new ArrayList<>();
//        auktionsSammlung = mSammlung.getSammlung().;
        Kunstwerk imageForSale = mSammlung.getSammlung().get(x);
        //mSammlung.removeImageFromList(imageForSale);

        return imageForSale;
    }

    public void auktionFinishedImageSold(Kunstwerk soldImage){
        mSammlung.removeImageFromList(soldImage);
    }

    public void addImageToPlayer(Kunstwerk boughtImage){
        mPlayer.addImageToPlayerArtList(boughtImage);
    }

}
