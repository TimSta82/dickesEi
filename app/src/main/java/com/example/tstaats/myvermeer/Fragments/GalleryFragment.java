package com.example.tstaats.myvermeer.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tstaats.myvermeer.Adapters.GalleryAdapter;
import com.example.tstaats.myvermeer.Common.KunstSammlung;
import com.example.tstaats.myvermeer.Common.Kunstwerk;
import com.example.tstaats.myvermeer.MainActivity;
import com.example.tstaats.myvermeer.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private static final String TAG = "GalleryFragment";
    public static final int SPAN_COUNT = 3;


    private MainActivity mainActivity;
    private KunstSammlung mSammlung;
    private List<Kunstwerk> artList;
    private Button backBtn;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        backBtn = view.findViewById(R.id.button_recyc_back);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.fragmentSwitcher(new HomeFragment(), "HomeFragment");
            }
        });


/*
        artList = new ArrayList<>();
        artList.add(new Kunstwerk(R.drawable.xwef,"Hans", "Title1", 10000));
        artList.add(new Kunstwerk(R.drawable.xabstract,"Skeletor", "Title3", 10000));
        artList.add(new Kunstwerk(R.drawable.xhkhk,"Victor", "Title4", 10000));
        artList.add(new Kunstwerk(R.drawable.xhuhh,"Michi", "Title5", 10000));
        artList.add(new Kunstwerk(R.drawable.xmonet,"Flo", "Title6", 10000));
        artList.add(new Kunstwerk(R.drawable.xotto,"Mene", "Title7", 10000));
        artList.add(new Kunstwerk(R.drawable.xpicknik,"Dörpie", "Title8", 10000));
        artList.add(new Kunstwerk(R.drawable.x_big,"Gustav", "Title9", 10000));
        artList.add(new Kunstwerk(R.drawable.xschiff,"Hawk", "Title10", 10000));
        artList.add(new Kunstwerk(R.drawable.xppp,"Arni", "Title11", 10000));
        artList.add(new Kunstwerk(R.drawable.xvermeer,"Vermeer", "Title12", 10000));
        artList.add(new Kunstwerk(R.drawable.xsn005,"Pimmel", "Title13", 10000));
*/
        artList = new ArrayList<>();
        artList = mSammlung.getSammlung();
        RecyclerView myrv = (RecyclerView) view.findViewById(R.id.recyclerview_id);
        GalleryAdapter adapter = new GalleryAdapter(getActivity(), artList);
        myrv.setLayoutManager(new GridLayoutManager(getActivity(), SPAN_COUNT));
        myrv.setAdapter(adapter);


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity) getActivity();

        mSammlung = KunstSammlung.getInstance();
    }
}
