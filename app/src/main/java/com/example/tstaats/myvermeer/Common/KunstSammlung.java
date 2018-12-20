package com.example.tstaats.myvermeer.Common;

import com.example.tstaats.myvermeer.R;

import java.util.ArrayList;

public class KunstSammlung {

    private static final String TAG = "KunstSammlung";

    public static KunstSammlung instance;

    private ArrayList<Kunstwerk> sammlung = new ArrayList<>();

    private KunstSammlung(){

    }

    public static KunstSammlung getInstance(){
        if (instance == null){
            L.l(TAG, String.valueOf(R.string.create_instance));
            instance = new KunstSammlung();
        }
        L.l(TAG, String.valueOf(R.string.instance_exists));
        return instance;
    }

    public ArrayList<Kunstwerk> getSammlung(){
        return sammlung;
    }

    public void removeImageFromList(Kunstwerk image){

        if (sammlung.isEmpty()){
            L.l(TAG, "Sammlung ist leer");
        } else {
            L.l(TAG, "Bild aus Gesamt-Sammlung entfernt: Künstler: " + image.getArtist() + " Bild titel: " + image.getImageName());
            sammlung.remove(image);
        }
    }

    public void addImageToList(Kunstwerk image){
        L.l(TAG, "Bild der Gesamt-Sammlung hinzugefügt: Künstler: " + image.getArtist() + " Bild titel: " + image.getImageName());
        sammlung.add(image);
    }

    public void setSammlung() {

        L.l(TAG, "Setup the default list, all images included");
        sammlung.add(new Kunstwerk(R.drawable.banksy, "Banky", "Ballon Girl", 0, 0));
        sammlung.add(new Kunstwerk(R.drawable.aftersunset, "Hans de Kock", "After Sunset", 5000, 0));
        sammlung.add(new Kunstwerk(R.drawable.claudemonet, "Claude Monet", "Boote bla", 15000, 0));
        sammlung.add(new Kunstwerk(R.drawable.vangogh, "Vincent van Gogh", "Ladidadi I like 2 Party", 22000, 0));
        sammlung.add(new Kunstwerk(R.drawable.twoshoemakers, "Harry Pistole", "Schuhmacher machen Schuhe", 17000, 0));
        sammlung.add(new Kunstwerk(R.drawable.derschrei, "Edward Munch", "Kreisch!!", 31000, 0));
        sammlung.add(new Kunstwerk(R.drawable.kaese, "Harald Edamer", "Harzer Rolle mit Nuss", 31000, 0));
        sammlung.add(new Kunstwerk(R.drawable.xwef,"Hans", "Title1", 11000, 0));
        sammlung.add(new Kunstwerk(R.drawable.xabstract,"Skeletor", "Title3", 7000, 0));
        sammlung.add(new Kunstwerk(R.drawable.xhkhk,"Victor", "Title4", 6000, 0));
        sammlung.add(new Kunstwerk(R.drawable.xhuhh,"Michi", "Title5", 8000, 0));
        sammlung.add(new Kunstwerk(R.drawable.xmonet,"Flo", "Title6", 2000, 0));
        sammlung.add(new Kunstwerk(R.drawable.xotto,"Mene", "Title7", 13000, 0));
        sammlung.add(new Kunstwerk(R.drawable.xpicknik,"Dörpie", "Title8", 17000, 0));
        sammlung.add(new Kunstwerk(R.drawable.x_big,"Gustav", "Title9", 3000, 0));
        sammlung.add(new Kunstwerk(R.drawable.xschiff,"Hawk", "Title10", 500, 0));
        sammlung.add(new Kunstwerk(R.drawable.xppp,"Arni", "Title11", 5700, 0));
        sammlung.add(new Kunstwerk(R.drawable.xvermeer,"Vermeer", "Title12", 10000, 0));
        sammlung.add(new Kunstwerk(R.drawable.xsn005,"Pimmel", "Title13", 16000, 0));
    }

}
