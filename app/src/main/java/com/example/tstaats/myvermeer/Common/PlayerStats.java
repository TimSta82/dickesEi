package com.example.tstaats.myvermeer.Common;

import android.util.Log;
import android.widget.ImageButton;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PlayerStats {

    private static final String TAG = "PlayerStats";

    public static PlayerStats instance;
    private String playerName;

    // Aufenthaltsort
    private City currentLocation;

    // bargeld
    private double playerMoney;
    // bankguthaben
    private double playerCredit;

    // aktien anzahl
    private int playerLloyd;
    private int playerStar;
    private int playerHanse;
    private int playerRoyal;

    // Rohstoff anzahl
    private int playerTabak;
    private int playerTee;
    private int playerKaffee;
    private int playerKakao;

    private ArrayList<Kunstwerk> playerArtList = new ArrayList<>();
    private ArrayList<Plant> playerPlantList = new ArrayList<>();


    private PlayerStats(){

    }

    public static PlayerStats getInstance(){
        if (instance == null){
            Log.d(TAG, "getInstance: instance created");
            instance = new PlayerStats();
        }
        Log.d(TAG, "getInstance: instance allready exists");
        return instance;
    }

    public City getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(City currentLocation) {
        this.currentLocation = currentLocation;
    }

    public ArrayList<Kunstwerk> getPlayerArtList() {
        return playerArtList;
    }

    public ArrayList<Plant> getPlayerPlantList() {
        return playerPlantList;
    }

    public void addPlantToPlayerPlantList(Plant plantage) {
        playerPlantList.add(plantage);
    }

    public Plant getCurrentPlant(){

        Plant current = new Plant();
        ArrayList<Plant> list = getPlayerPlantList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getVillageName() == getCurrentLocation().getCityName() && list.get(i).isBought()){

                current = list.get(i);

                return current;
            }

        }

        return null;
    }

    public void addImageToPlayerArtList(Kunstwerk bild){

        L.l(TAG, "Bild der SpielerSammlung hinzugefügt: " + bild.getImageName());
        playerArtList.add(bild);
    }

    public void removeImageFromPlayerArtList(Kunstwerk bild){

        L.l(TAG, "Bild aus der SpielerSammlung entfernt: " + bild.getImageName());
        playerArtList.remove(bild);
    }



    public double getPlayerCredit() {
        return playerCredit;
    }

    public void setPlayerCredit(double playerCredit) {
        this.playerCredit = playerCredit;
    }

    public int getPlayerTabak() {
        return playerTabak;
    }

    public void setPlayerTabak(int playerTabak) {
        this.playerTabak = playerTabak;
    }

    public int getPlayerTee() {
        return playerTee;
    }

    public void setPlayerTee(int playerTee) {
        this.playerTee = playerTee;
    }

    public int getPlayerKaffee() {
        return playerKaffee;
    }

    public void setPlayerKaffee(int playerKaffee) {
        this.playerKaffee = playerKaffee;
    }

    public int getPlayerKakao() {
        return playerKakao;
    }

    public void setPlayerKakao(int playerKakao) {
        this.playerKakao = playerKakao;
    }

    public int getPlayerLloyd() {
        return playerLloyd;
    }

    public void setPlayerLloyd(int playerLloyd) {
        this.playerLloyd = playerLloyd;
    }

    public int getPlayerStar() {
        return playerStar;
    }

    public void setPlayerStar(int playerStar) {
        this.playerStar = playerStar;
    }

    public int getPlayerHanse() {
        return playerHanse;
    }

    public void setPlayerHanse(int playerHanse) {
        this.playerHanse = playerHanse;
    }

    public int getPlayerRoyal() {
        return playerRoyal;
    }

    public void setPlayerRoyal(int playerRoyal) {
        this.playerRoyal = playerRoyal;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public double getPlayerMoney() {
        return playerMoney;
    }

    public void setPlayerMoney(double playerMoney) {
        this.playerMoney = playerMoney;
    }

    public String rawMaterialsToString(){
        return "Tabak: " + instance.getPlayerTabak() + "\nTee: " + instance.getPlayerTee() +
                "\nKaffee: " + instance.getPlayerKaffee() + "\nKakao: " + instance.getPlayerKakao() + "\n";
    }

    public String stockDetailsToString(){
        return "LLoyd: " + instance.getPlayerLloyd() + "\nStar: " + instance.getPlayerStar() +
                  "\nHanse: " + instance.getPlayerHanse() + "\nRoyal: " + instance.getPlayerRoyal() + "\n";
    }

    public String kunstwerkListToString() {
        String sammlung = "";
        for (int i = 0; i < playerArtList.size(); i++) {
            sammlung += "Künstler: " + playerArtList.get(i).getArtist() + "\nWerk: "
                    + playerArtList.get(i).getImageName() + "\nWert: " + playerArtList.get(i).getImageValue() +
                    "\nPreis auf letzter Auktion: " + playerArtList.get(i).getImageSoldFor() + "\n\n";
        }
        return sammlung;
    }

    public String plantagenListToString(){
        String plantagen = "";
        for (int i = 0; i < playerPlantList.size(); i++) {
            plantagen += "Stadt: " + playerPlantList.get(i).getVillageName() + "\n";
        }
        return "Plantagen in folgenden Städten: \n" + plantagen;
    }

    public String playerMoneyFormattedToString(){
        int euro = (int)Math.abs(instance.playerMoney);
        double cent = instance.playerMoney * 100;
        cent = Math.abs(cent % 100);
        DecimalFormat df = new DecimalFormat("00");
        return ((instance.getPlayerMoney() < 0) ? "-" : "") + euro + "," + df.format(cent) + "€";
    }

    public String playerCreditFormattedToString(){
        int euro = (int)Math.abs(instance.playerCredit);
        double cent = instance.playerCredit * 100;
        cent = Math.abs(cent % 100);
        DecimalFormat df = new DecimalFormat("00");
        return ((instance.getPlayerCredit() < 0) ? "-" : "") + euro + "," + df.format(cent) + "€";

    }

    @Override
    public String toString() {

        return "Name: " + instance.getPlayerName() + "\n";


    }
}
